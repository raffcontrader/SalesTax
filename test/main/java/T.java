import java.io.UnsupportedEncodingException;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import springfox.documentation.spring.web.json.Json;
import test.Topic;

public class T {

	public static void main(String[]args) {
		Json mod=new Json("MODIFICA");
		Json ins=new Json("INSERISCI");
		
		//Non si deve chiamare Topic2 ma Bus
		Topic bus=new Topic();
		//Il nome dell'exchange e dell'host di default (nel costruttore vuoto del bus) e specifici (nel 
		//costruttore con parametri sempre del bus)
		
		//Mi sottoscivo solo a pagamenti.modifica e ricevo entrambe (#)

        DeliverCallback deliverCallback = (consumerTag, delivery) -> elaborateMessage(delivery);
		//bus.sottoscrivi("#", deliverCallback);

		
		

		/*bus.accoda("busTest", "mio msg");
		
		DeliverCallback deliverCallback2 = (consumerTag, delivery) -> elaborateMessageAccoded(delivery);
		
		bus.deaccoda(deliverCallback2);*/
		

		DeliverCallback deliverCallback2 = (consumerTag, delivery) -> elaborateMessageAccoded(delivery);
		
		//bus.inizializzaCoda("busTest");
		
		//bus.accoda("busTest", "Prova1");
		
		//bus.accoda("busTest", "Prova2");

		//bus.deaccoda("busTest", deliverCallback2);
		//bus.deaccoda("busTest", deliverCallback2);
		//bus.deaccoda("busTest", deliverCallback2);
		//bus.deaccoda("busTest", deliverCallback2);
		//bus.deaccoda("busTest", deliverCallback2);
				
		//LO RICEVERO'
		bus.pubblica("pagamenti.modifica", mod);
		
		//LO RICEVERO'
		//bus.pubblica("pagamenti.inserisci", ins);

		bus.sottoscrivi("#", deliverCallback);
		bus.pubblica("pagamenti.modifica", mod);
		//STOP CALLBACK
		//bus.disiscriviti();
	}
	
	private static Json elaborateMessage(Delivery delivery) {
		String message="";
		try {
			message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received1 '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Json(message);   
	}
	
	
	private static Json elaborateMessageAccoded(Delivery delivery) {
		String message="";
		try {
			message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received Accoded '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
			//INVIO ACK
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Json(message);   
	}
	
	
	
	
	
}
