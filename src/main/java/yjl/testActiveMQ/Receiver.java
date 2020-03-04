package yjl.testActiveMQ;

import javax.jms.Connection;
import javax.jms.Session;

public abstract class Receiver {
	String url;
	Connection connection;
	Session session;
	public abstract void receive();
}
