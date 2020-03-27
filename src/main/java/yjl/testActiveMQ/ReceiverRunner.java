package yjl.testActiveMQ;

public class ReceiverRunner extends Thread {           //订阅者线程  用于并行测试
	Receiver receiver;
	public volatile boolean exit = false; 
	public ReceiverRunner() {
		// TODO Auto-generated constructor stub
	}
	public ReceiverRunner(Receiver receiver) {
		this.receiver=receiver;
	}
	
	@Override
	public void run() {
		while(!exit) {
			//System.out.println("线程未终止");
			receiver.receive();

		}
		//System.out.println("线程已终止");
	}
	
}
