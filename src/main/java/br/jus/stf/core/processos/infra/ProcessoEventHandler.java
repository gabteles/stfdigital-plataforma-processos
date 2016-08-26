package br.jus.stf.core.processos.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import br.jus.stf.core.processos.domain.Processo;
import br.jus.stf.core.processos.domain.ProcessoRepository;
import br.jus.stf.core.shared.eventos.EnvolvidoRegistrado;
import br.jus.stf.core.shared.eventos.PeticaoRegistrada;
import br.jus.stf.core.shared.eventos.ProcessoAutuado;
import br.jus.stf.core.shared.eventos.ProcessoRegistrado;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 20.02.2016
 */
@Component
@Profile("!test")
@EnableBinding(ProcessoEventHandler.Channels.class)
public class ProcessoEventHandler {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	@StreamListener(RemessaRegistrada.EVENT_KEY)
	public void handle(RemessaRegistrada event) {
		processoRepository.save(new Processo(event.getProtocoloId(), event.getProtocolo()));
	}

	@StreamListener(PeticaoRegistrada.EVENT_KEY)
	public void handle(PeticaoRegistrada event) {
		processoRepository.save(new Processo(event.getProtocoloId(), event.getProtocolo()));
	}

	@StreamListener(EnvolvidoRegistrado.EVENT_KEY)
	public void handle(EnvolvidoRegistrado event) {
		doIndexByProtocoloId(event.getProtocoloId(), processo -> processo.getPartes().add(event.getNome()));
	}

	@StreamListener(ProcessoRegistrado.EVENT_KEY)
	public void handle(ProcessoRegistrado event) {
		doIndexByProtocoloId(event.getProtocoloId(), processo -> processo.setProcessoId(event.getProcessoId()));
		
		Processo processo = processoRepository.findByProcessoId(event.getProcessoId());
		
		// Ao lançar a exceção abaixo forçamos uma nova tentativa no futuro, já que o Rabbit só vai remover a mensagem da fila se o processamento ocorrer sem problemas
        if (processo == null) {
        	throw new IllegalStateException(String.format("Processo [%s] ainda não foi indexado.", event.getProcessoId()));
        }
    }

	@StreamListener(ProcessoAutuado.EVENT_KEY)
	public void handle(ProcessoAutuado event) {
		doIndexByProcessoId(event.getProcessoId(), processo -> processo.setNumero(event.getNumero()));
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Classes e Métodos Utilitários...
	
	private void doIndexByProtocoloId(Long protocoloId, IndexAction action) {
		Processo processo = processoRepository.findByProtocoloId(protocoloId);
		
        if (processo == null) {
        	throw new IllegalStateException(String.format("Processo de Protocolo ID [%s] ainda não foi indexado.", protocoloId));
        }
        
		action.execute(processo);
		
		processoRepository.save(processo);
	}
	
	private void doIndexByProcessoId(String processoId, IndexAction action) {
		Processo processo = processoRepository.findByProcessoId(processoId);
		
		// Ao lançar a exceção abaixo forçamos uma nova tentativa no futuro, já que o Rabbit só vai remover a mensagem da fila se o processamento ocorrer sem problemas
        if (processo == null) {
        	throw new IllegalStateException(String.format("Processo [%s] ainda não foi indexado.", processoId));
        }
        
		action.execute(processo);
		
		processoRepository.save(processo);
	}
	
	@FunctionalInterface
	private interface IndexAction {

		void execute(Processo processo);
		
	}
	
	/**
	 * Configuração dos canais que serão usados pelo serviço de peticionamento
	 * para publicação de eventos de domínio.
	 * 
	 * @author Rodrigo Barreiros
	 * 
	 * @since 1.0.0
	 * @since 18.08.2016
	 */
	public interface Channels {

		/**
		 * O canal que será usado para publicação de eventos do tipo {@link PeticaoRegistrada}.
		 * 
		 * @return o canal para as petições registradas
		 */
		@Input(PeticaoRegistrada.EVENT_KEY)
		SubscribableChannel peticaoRegistrada();

		/**
		 * O canal que será usado para publicação de eventos do tipo {@link EnvolvidoRegistrado}.
		 * 
		 * @return o canal para os envolvidos registrados
		 */
		@Input(EnvolvidoRegistrado.EVENT_KEY)
		SubscribableChannel envolvidoRegistrado();

		@Input(RemessaRegistrada.EVENT_KEY)
		SubscribableChannel remessaRegistrada();

		@Input(ProcessoRegistrado.EVENT_KEY)
		SubscribableChannel processoRegistrado();

		@Input(ProcessoAutuado.EVENT_KEY)
		SubscribableChannel processoAutuado();

	}

}
