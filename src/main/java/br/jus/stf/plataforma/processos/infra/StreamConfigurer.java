package br.jus.stf.plataforma.processos.infra;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

import br.jus.stf.core.shared.eventos.EnvolvidoRegistrado;
import br.jus.stf.core.shared.eventos.PeticaoRegistrada;
import br.jus.stf.core.shared.eventos.ProcessoAutuado;
import br.jus.stf.core.shared.eventos.ProcessoDistribuido;
import br.jus.stf.core.shared.eventos.ProcessoRegistrado;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;

/**
 * Configuração dos canais que serão usados pelo serviço de pesquisa
 * para recebimento de eventos de domínio e indexação das
 * informações relacionadas aos processos.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.08.2016
 */
@EnableBinding(StreamConfigurer.Channels.class)
public class StreamConfigurer {
	
	/**
	 * Declaração dos canais mencionados acima.
	 * 
	 * @author Rodrigo Barreiros
	 * 
	 * @since 1.0.0
	 * @since 18.08.2016
	 */
	public interface Channels {

		/**
		 * O canal que será usado para receber eventos do tipo {@link PeticaoRegistrada}.
		 * 
		 * @return o canal para as petições registradas
		 */
		@Input(PeticaoRegistrada.EVENT_KEY)
		public SubscribableChannel peticaoRegistrada();

		/**
		 * O canal que será usado para receber eventos do tipo {@link EnvolvidoRegistrado}.
		 * 
		 * @return o canal para os envolvidos registrados
		 */
		@Input(EnvolvidoRegistrado.EVENT_KEY)
		public SubscribableChannel envolvidoRegistrado();

		/**
		 * O canal que será usado para receber eventos do tipo {@link RemessaRegistrada}.
		 * 
		 * @return o canal para as remessas registradas
		 */
		@Input(RemessaRegistrada.EVENT_KEY)
		public SubscribableChannel remessaRegistrada();

		/**
		 * O canal que será usado para receber eventos do tipo {@link ProcessoRegistrado}.
		 * 
		 * @return o canal para os processos registrados
		 */
		@Input(ProcessoRegistrado.EVENT_KEY)
		public SubscribableChannel processoRegistrado();

		/**
		 * O canal que será usado para receber eventos do tipo {@link ProcessoAutuado}.
		 * 
		 * @return o canal para os processos autuados
		 */
		@Input(ProcessoAutuado.EVENT_KEY)
		public SubscribableChannel processoAutuado();
		
		/**
		 * O canal que será usado para receber eventos do tipo {@link ProcessoDistribuido}.
		 * 
		 * @return o canal para os processos distribuídos
		 */
		@Input(ProcessoDistribuido.EVENT_KEY)
		public SubscribableChannel processoDistribuido();

	}

}
