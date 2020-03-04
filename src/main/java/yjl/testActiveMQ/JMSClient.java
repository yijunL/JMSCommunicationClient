package yjl.testActiveMQ;

import java.util.Scanner;

import javax.jms.JMSException;

public class JMSClient {

    public static void help() {
        System.out.println("Usage:java -jar JMSClient.jar sender/receiver/topicSender/topicReceiver/fileSender/fileReceiver");
        System.out.println("sender:向jms队列发送消息");
        System.out.println("receiver:从jms队列中取出消息");
        System.out.println("topicSender:向jms主题发送消息");
        System.out.println("topicReceiver:从jms主题中取出消息");
        System.out.println("fileSender:向jms文件队列传输文件");
        System.out.println("fileReceiver:从jms文件队列接受文件");
    }

    public static void what(String[] cmd) throws JMSException {
        if (cmd.length == 0) {
            help();
            return;
        }
        String mode = cmd[0];
        if ("sender".equalsIgnoreCase(mode)) {
            JMSSender sender = new JMSSender();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("input you message(q to exist):");
                String msg = sc.nextLine();
                if ("q".equalsIgnoreCase(msg)) {
//                	sender.terminate();
                    return;
                }
                sender.sendMessage(msg);;
//                System.out.println("message send success");
            }
        } else if ("receiver".equalsIgnoreCase(mode)) {
            JMSReceiver consumer = new JMSReceiver();
			ReceiverRunner receiverRunner=new ReceiverRunner(consumer);
			receiverRunner.start();
			Scanner sc = new Scanner(System.in);
			while (true) {
	             System.out.println("input q to exist :");
	             String msg = sc.nextLine();
	             if ("q".equalsIgnoreCase(msg)) {
	            	 receiverRunner.exit=true;
	                 return;
	                }
	        }
            
        } else if ("topicSender".equalsIgnoreCase(mode)) {
            JMSTopicSender sender = new JMSTopicSender();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("input you message(q to exist):");
                String msg = sc.nextLine();
                if ("q".equalsIgnoreCase(msg)) {
                    return;
                }
                sender.sendMessage(msg);
            }
        } else if ("topicReceiver".equalsIgnoreCase(mode)) {
            JMSTopicReceiver consumer = new JMSTopicReceiver();
            consumer.receive();
        }
        else if("fileSender".equalsIgnoreCase(mode)) {
        	JMSFileSender sender=new JMSFileSender();
        	Scanner sc = new Scanner(System.in);
        	while (true) {
        		sender.sendFile();
                System.out.println("input any key(q to exist) to send another file: ");
                String msg = sc.nextLine();
                if ("q".equalsIgnoreCase(msg)) {
                    return;
                }
                
            }
        }
        else if("fileReceive".equalsIgnoreCase(mode)) {
        	JMSFileReceiver receiver=new JMSFileReceiver();
        	receiver.receiveFile();
        }
    }
}
