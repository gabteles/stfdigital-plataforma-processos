package br.jus.stf.plataforma.processos.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.jus.stf.core.shared.eventos.EnvolvidoRegistrado;
import br.jus.stf.core.shared.eventos.PeticaoRegistrada;
import br.jus.stf.core.shared.eventos.ProcessoAutuado;
import br.jus.stf.core.shared.eventos.ProcessoDistribuido;
import br.jus.stf.core.shared.eventos.ProcessoRegistrado;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;
import br.jus.stf.plataforma.processos.domain.Distribuicao;
import br.jus.stf.plataforma.processos.domain.Processo;
import br.jus.stf.plataforma.processos.domain.ProcessoRepository;

/**
 * Trata todos os eventos que tenham relação com processos e suas informações, promovendo
 * a indexação das propriedades que serão, posteriormente, usadas para pesquisa
 * de processos.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 20.02.2016
 */
@Component
@Profile("!test")
public class ProcessoEventHandler {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	/**
	 * Cria o índice do processo sempre que uma nova remessa for registrada.
	 * 
	 * @param event o registro da remessa
	 */
	@StreamListener(RemessaRegistrada.EVENT_KEY)
	public void handle(RemessaRegistrada event) {
		processoRepository.save(new Processo(event.getProtocoloId(), event.getProtocolo()));
	}

	/**
	 * Cria o índice do processo sempre que uma nova petição for registrada.
	 * 
	 * @param event o registro da petição
	 */
	@StreamListener(PeticaoRegistrada.EVENT_KEY)
	public void handle(PeticaoRegistrada event) {
		processoRepository.save(new Processo(event.getProtocoloId(), event.getProtocolo()));
	}

	/**
	 * Indexa o processo pelo nome de cada envolvido registrado.
	 * 
	 * @param event o registro do envolvido
	 */
	@StreamListener(EnvolvidoRegistrado.EVENT_KEY)
	public void handle(EnvolvidoRegistrado event) {
		doIndexByProtocoloId(event.getProtocoloId(), processo -> processo.getPartes().add(event.getNome()));
	}

	/**
	 * Indexa o processo pelo ID do processo judicial recém criado.
	 * 
	 * @param event o registro do processo judicial
	 */
	@StreamListener(ProcessoRegistrado.EVENT_KEY)
	public void handle(ProcessoRegistrado event) {
		doIndexByProtocoloId(event.getProtocoloId(), processo -> processo.setProcessoId(event.getProcessoId()));
		
		Processo processo = processoRepository.findByProcessoId(event.getProcessoId());
		
		// Ao lançar a exceção abaixo, forçamos uma nova tentativa no futuro, já que o Rabbit só vai remover a mensagem da 
		// fila se o processamento ocorrer sem problemas
        if (processo == null) {
        	throw new IllegalStateException(String.format("Processo [%s] ainda não foi indexado.", event.getProcessoId()));
        }
    }

	/**
	 * Indexa o processo pelo número do processo gerado na autuação.
	 * 
	 * @param event o registro do processo autuado
	 */
	@StreamListener(ProcessoAutuado.EVENT_KEY)
	public void handle(ProcessoAutuado event) {
		doIndexByProcessoId(event.getProcessoId(), processo -> processo.setNumero(event.getNumero()));
	}
	
	/**
	 * Indexa o processo pelo número do processo gerado na autuação.
	 * 
	 * @param event o registro do processo autuado
	 */
	@StreamListener(ProcessoDistribuido.EVENT_KEY)
	public void handle(ProcessoDistribuido event) {
		Distribuicao distribuicao = new Distribuicao(event.getRelatorId(), event.getRelator(), event.getData());
		doIndexByProcessoId(event.getProcessoId(), processo -> processo.getDistribuicoes().add(distribuicao));
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Classes e Métodos Utilitários...
	
	/**
	 * Executa uma ação de indexação sobre um processo identificado a partir de um dado ID de protocolo.
	 * 
	 * @param protocoloId o identificador do protocolo do processo
	 * @param action a ação de indexação
	 */
	private void doIndexByProtocoloId(Long protocoloId, IndexAction action) {
		Processo processo = processoRepository.findByProtocoloId(protocoloId);
		
		// Ao lançar a exceção abaixo, forçamos uma nova tentativa no futuro, já que o Rabbit só vai remover a mensagem da 
		// fila se o processamento ocorrer sem problemas
        if (processo == null) {
        	throw new IllegalStateException(String.format("Processo de Protocolo ID [%s] ainda não foi indexado.", protocoloId));
        }
        
		action.execute(processo);
		
		processoRepository.save(processo);
	}
	
	/**
	 * Executa uma ação de indexação sobre um processo recuperado a partir de seu identificador.
	 * 
	 * @param processoId o identificador do processo
	 * @param action a ação de indexação
	 */
	private void doIndexByProcessoId(String processoId, IndexAction action) {
		Processo processo = processoRepository.findByProcessoId(processoId);
		
		// Ao lançar a exceção abaixo, forçamos uma nova tentativa no futuro, já que o Rabbit só vai remover a mensagem da 
		// fila se o processamento ocorrer sem problemas
        if (processo == null) {
        	throw new IllegalStateException(String.format("Processo [%s] ainda não foi indexado.", processoId));
        }
        
		action.execute(processo);
		
		processoRepository.save(processo);
	}
	
	/**
	 * Representa uma ação de indexação de um processo. Uma ação de indexação seria qualquer 
	 * operação cujo objetivo é indexar uma determinada propriedade de um processo.
	 * 
	 * <p>Por exemplo, a indexação de processo por seu número seria uma ação indexação.
	 * 
	 * @author Rodrigo Barreiros
	 * 
	 * @since 1.0.0
	 * @since 20.02.2016
	 */
	@FunctionalInterface
	private interface IndexAction {

		/**
		 * Executa a indexação das propriedades do processo.
		 * 
		 * @param processo o processo que será indexado
		 */
		void execute(Processo processo);
		
	}
	
}
