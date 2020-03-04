//package yjl.testActiveMQ;
//
//import javax.jms.JMSException;
//
//public class Test {
//	public static void main(String[] args) throws JMSException, InterruptedException {
//		JMSSender jmsSender=new JMSSender();
//		jmsSender.sendMessage("hello");
//		jmsSender.sendMessage("hello2");
//		JMSReceiver jmsReceiver=new JMSReceiver();
////		JMSTopicSender topicSender=new JMSTopicSender();
////		topicSender.sendMessage("hello");
////		JMSTopicReceiver topicReceiver=new JMSTopicReceiver();
////		topicReceiver.receive();
//		ReceiverRunner receiverRunner=new ReceiverRunner(jmsReceiver);
//		receiverRunner.start();
//		Thread.sleep(1000);
//		receiverRunner.exit=true;
//		jmsSender.sendMessage("hello3");
//	}
//}
