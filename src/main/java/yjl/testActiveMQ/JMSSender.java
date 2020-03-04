package yjl.testActiveMQ;

import java.time.LocalDateTime;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class JMSSender{           //基于JMS的消息发送者
	public static final String user = "system";
	public static final String password = "manager";
	public static final String url = "tcp://localhost:61616";
	public static final String queueName = "test_queue";
	public static final String messageBody = "Hello JMS!";
	public static final boolean transacted = false;
	public static final boolean persistent = false;
	Connection connection = null;
	Session session = null;
	MessageProducer producer=null;
	Destination destination=null;
	
	private void init() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        connection = connectionFactory.createConnection();
        connection.start();
        
        // create the session
        session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(queueName);
        
        // create the producer
        producer = session.createProducer(destination);
        if (persistent){
        	producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        }else{
        	producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }
	}
	
	public void sendMessage(String msg) throws JMSException {
		 if (this.producer == null)
            this.init();
		 TextMessage message = session.createTextMessage(msg);    
         // send the message
		 System.out.println(LocalDateTime.now()+"  Send message: " +  message.getText());
         producer.send(message); 
	}
	
	public void terminate() throws JMSException {
		session.close();
		connection.close();  
	}
}