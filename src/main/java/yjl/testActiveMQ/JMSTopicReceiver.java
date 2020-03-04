package yjl.testActiveMQ;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.jms.Connection;  
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.MessageConsumer;  
import javax.jms.MessageListener;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
import javax.jms.Topic;  
  
import org.apache.activemq.ActiveMQConnectionFactory;  
  
/** 
 * 持久订阅设置唯一的客户端ID和订阅者ID。 
 */  
public class JMSTopicReceiver extends Receiver {           //基于JMS的主题订阅者
  
	public static final String url = "tcp://localhost:61616";
//	public static final String topicName="topicTest";
	Connection connection=null;
	Session session=null;
	
    public void receive() {  
        String clientId = LocalTime.now().toString();  
          
        // 连接到ActiveMQ服务器  
        try {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);  
        connection = factory.createConnection();  
        //客户端ID,持久订阅需要设置  
        connection.setClientID(clientId);  
        connection.start();  
        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);  
        // 创建主题  
        Topic topic = session.createTopic("topicTest");  
        // 创建持久订阅,指定客户端ID。  
        MessageConsumer consumer = session.createDurableSubscriber(topic,clientId); 
        consumer.setMessageListener(new MessageListener() {  
            // 订阅接收方法  
            public void onMessage(Message message) {  
                TextMessage tm = (TextMessage) message;  
                try {  
                    System.out.println(LocalDateTime.now()+"  Received message: " + tm.getText());  
                } catch (JMSException e) {  
                    e.printStackTrace();  
                }  
            }  
        }); 
        }
        catch (Exception e){
    		e.printStackTrace();
    	}
         
    }

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		try {
			session.close();
			connection.close(); 
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}  
}  