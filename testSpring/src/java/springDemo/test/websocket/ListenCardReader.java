package springDemo.test.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/civilIdReader")
public class ListenCardReader {
	
	@OnMessage
	public void onMessage(Session session, String msg) {
		System.out.println(msg);
	}

	@OnOpen
	public void open(Session session) {
		System.out.println("New session opened: " + session.getId());
	}

	@OnError
	public void error(Session session, Throwable t) {
		System.err.println("Error on session " + session.getId());

	}

	@OnClose
	public void closedConnection(Session session) {
		System.out.println("session closed: " + session.getId());
	}
}
