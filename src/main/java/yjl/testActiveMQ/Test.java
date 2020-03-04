package yjl.testActiveMQ;

public class Test {
	public static void main(String[] args) {
		JMSReceiver jmsReceiver=new JMSReceiver();
		jmsReceiver.receive();
	}
}
