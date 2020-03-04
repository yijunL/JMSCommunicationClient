package yjl.testActiveMQ;

import java.time.LocalDateTime;

import javax.jms.Connection;  
import javax.jms.DeliveryMode;  
import javax.jms.JMSException;  
import javax.jms.MessageProducer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
import javax.jms.Topic;  
  
import org.apache.activemq.ActiveMQConnectionFactory;  
  
public class JMSTopicSender {         //基于JMS的主题发布者
	Session session =null;
	MessageProducer producer=null;
	Connection connection =null;
	public static final String url = "tcp://localhost:61616";
	public static final String topicName="topicTest";
	
	public JMSTopicSender() {
        super();
    }
	
//	private void init() throws JMSException {
//		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);  
//        connection = factory.createConnection();  
//        connection.start();  
//        session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
//     // 创建主题  
//        Topic topic = session.createTopic(topicName);
//        producer = session.createProducer(topic);  
//        // NON_PERSISTENT 非持久化 PERSISTENT 持久化,发送消息时用使用持久模式  
//        producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
//	}
	
	public void sendMessage(String msg) throws JMSException {
        TextMessage textMsg;
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);  
        connection = factory.createConnection();  
        connection.start();  
        session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
        // 创建主题  
        Topic topic = session.createTopic(topicName);
        producer = session.createProducer(topic);  
        // NON_PERSISTENT 非持久化 PERSISTENT 持久化,发送消息时用使用持久模式  
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
        try {
//            if (this.producer == null) {
//                this.init();
//            }
            textMsg = session.createTextMessage();
            textMsg.setText(msg);
            System.out.println(LocalDateTime.now()+"  Sent message: " + textMsg.getText()); 
            producer.send(textMsg);
            session.commit();           
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        session.close();
		connection.close();  
	}
//	public void terminate() throws JMSException {
//		session.close();
//		connection.close();  
//	}
}  
