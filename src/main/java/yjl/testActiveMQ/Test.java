package yjl.testActiveMQ;

import javax.jms.JMSException;

public class Test {
	public static void main(String[] args) throws JMSException {
		JMSSender jmsSender=new JMSSender();
		jmsSender.sendMessage("hello");
		JMSReceiver jmsReceiver=new JMSReceiver();
		jmsReceiver.receive();
//		ReceiverRunner receiverRunner=new ReceiverRunner(jmsReceiver);
//		receiverRunner.start();
//		receiverRunner.interrupt();
//		jmsSender.sendMessage("hello2");
	}
}
