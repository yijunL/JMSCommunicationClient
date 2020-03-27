package yjl.testActiveMQ;

import java.util.Scanner;

import javax.jms.JMSException;

public class JMSClient {

    public static void help() {
        System.out.println("Usage:java -jar JMSClient.jar p2p/topicSender/topicReceiver/fileSender/fileReceiver");
        System.out.println("p2p:点对点通讯");
        System.out.println("topicSender:向jms主题发送消息");
        System.out.println("topicReceiver:从jms主题中取出消息");
        System.out.println("fileSender:向jms文件队列传输文件");
        System.out.println("fileReceiver:从jms文件队列接受文件");
    }

    public static void main(String[] cmd) throws JMSException {
        if (cmd.length == 0) {
            help();
            return;
        }
        String mode = cmd[0];
        if ("p2p".equalsIgnoreCase(mode)) {
            JMSSender sender = new JMSSender();
            Scanner sc = new Scanner(System.in);
            System.out.println("input you userId to login:");
            String userId=sc.nextLine();

            //发送和接收并行运行
            JMSReceiver consumer = new JMSReceiver(userId);
			ReceiverRunner receiverRunner=new ReceiverRunner(consumer);
			receiverRunner.start();
			
			System.out.println("Enter receiver ID:");
            String destin=sc.nextLine();
            while (true) {
                System.out.println("input you message(/q to exist,/c to change receiver):");
                String msg = sc.nextLine();
                if ("/q".equalsIgnoreCase(msg)) {
                	receiverRunner.exit=true;
                	System.exit(0);
                    return;
                }
                if ("/c".equalsIgnoreCase(msg)) {
                	System.out.println("Enter receiver ID:");
                    destin=sc.nextLine();
                }
                sender.sendMessage(msg,destin);;
//                System.out.println("message send success");
            }
        } //else if ("receiver".equalsIgnoreCase(mode)) {               //已被废弃
//            JMSReceiver consumer = new JMSReceiver();
//			ReceiverRunner receiverRunner=new ReceiverRunner(consumer);
//			receiverRunner.start();
//			Scanner sc = new Scanner(System.in);
//			while (true) {
//	             System.out.println("input q to exist :");
//	             String msg = sc.nextLine();
//	             if ("q".equalsIgnoreCase(msg)) {
//	            	 receiverRunner.exit=true;
//	            	 System.exit(0);
//	                 return;
//	                }
//	        }
            
//        } 
    else if ("topicSender".equalsIgnoreCase(mode)) {
            JMSTopicSender sender = new JMSTopicSender();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("input you message(/q to exist):");
                String msg = sc.nextLine();
                if ("/q".equalsIgnoreCase(msg)) {
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
                System.out.println("input any key(/q to exist) to send another file: ");
                String msg = sc.nextLine();
                if ("/q".equalsIgnoreCase(msg)) {
                    return;
                }
                
            }
        }
        else if("fileReceiver".equalsIgnoreCase(mode)) {
        	JMSFileReceiver receiver=new JMSFileReceiver();
        	receiver.receiveFile();
        }
        else {
        	help();
            return;
        }
    }
}
