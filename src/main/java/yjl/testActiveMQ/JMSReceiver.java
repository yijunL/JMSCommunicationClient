package yjl.testActiveMQ;

import java.time.LocalDateTime;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class JMSReceiver {                //基于JMS的消息发送者的消息接收者
//	public static final String user = "system";
//	public static final String password = "manager";
	public static String url = "tcp://localhost:61616";
//	public static final String queueName = "test_queue";
//	public static final boolean transacted = false;
//	public static final boolean persistent = false;
	Connection connection = null;
	Session session = null;
	
    public void receive(){    	
    	
    	try{
    		//url = "tcp://localhost:61616";
    		// create the connection
    	    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();
            
            // create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("test_queue");
            
            // create the producer
            MessageProducer producer = session.createProducer(destination);
//            if (persistent){
            	producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//            }else{
//            	producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//            }
            
            // create the consumer
            MessageConsumer consumer = session.createConsumer(destination);
            // blocking till receive the message
            Message recvMessage = consumer.receive();
            System.out.println(LocalDateTime.now()+"  Receive message: " + ((TextMessage)recvMessage).getText());

            
            
    	}catch (Exception e){
    		e.printStackTrace();
    	}
//    	finally{
//    		try{
//    			// close session and connection
//    		    if (session != null){
//    			    session.close();
//    		    }
//    		    if (connection != null){
//    			    connection.close();
//    		    }
//    		}catch (Exception e){
//    			e.printStackTrace();
//    		}
//    	}
    }
	public void terminate() {
		try {
			session.close();
			connection.close(); 
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}