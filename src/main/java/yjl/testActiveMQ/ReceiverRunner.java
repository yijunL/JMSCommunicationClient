//package yjl.testActiveMQ;
//
//public class ReceiverRunner extends Thread {           //订阅者线程  用于并行测试
//	Receiver receiver;
//	public ReceiverRunner() {
//		// TODO Auto-generated constructor stub
//	}
//	public ReceiverRunner(Receiver receiver) {
//		this.receiver=receiver;
//	}
//	
//	@Override
//	public void run() {
//		while(!Thread.currentThread().isInterrupted()) {
//			receiver.receive();
//		}
//	}
//	
//}
