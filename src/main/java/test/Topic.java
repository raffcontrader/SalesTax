package test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import springfox.documentation.spring.web.json.Json;

public class Topic {
	private String EXCHANGE_NAME;
	private String host;
	
	public Topic() {
		this.EXCHANGE_NAME="bus";
		this.host="localhost";
	}
	
	public Topic(String EXCHANGE_NAME, String host) {
		this.EXCHANGE_NAME=EXCHANGE_NAME;
		this.host=host;
	}
	
	
	
	public boolean pubblica(String routingKey, Json message) {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(host);
	    try (Connection connection = factory.newConnection();
	         Channel channel = connection.createChannel()) {

	        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
	        
	        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.value().getBytes("UTF-8"));
	        System.out.println(" [x] Sent '" + routingKey + "':'" + message.value() + "'");
	    }catch(Exception e) {
	    	System.out.println(e);
	    	return false;
	    }
		return true;
	}
	
	
	public void sottoscrivi(String bindingKey, DeliverCallback deliverCallback) {
		try {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        
        channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);        

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void inizializzaCoda(String QUEUE_NAME) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		try (Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, true, false, true, null);
		}catch(Exception e) {
			
		}
		
	}

	public void accoda(String QUEUE_NAME, String msg) {
		inizializzaCoda(QUEUE_NAME);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		try (Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel()) {
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
			System.out.println(" [x] Sent '" + msg + "'");
		}catch(Exception e) {
			
		}
		
	}
	
	public void deaccoda(String QUEUE_NAME, DeliverCallback deliverCallback) {
		inizializzaCoda(QUEUE_NAME);
		try {
			ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost(host);
	        Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();
	        channel.basicQos(1);
	        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { System.out.println("Receive Cancelled"); });
	       
		}catch(Exception e) {
				System.out.println(e);
		}
	}
		
	private static void ssj3(Delivery delivery) {
		//Invio ack
	}
		
	public void disiscriviti() {
		System.exit(1);
	}
	
	
	
}


