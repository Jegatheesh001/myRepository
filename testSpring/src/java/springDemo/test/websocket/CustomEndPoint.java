package springDemo.test.websocket;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/messenger")
public class CustomEndPoint {

	private static Map<String, Session> queue = new LinkedHashMap<String, Session>();
	private static Map<String, String> userMap = new LinkedHashMap<String, String>();
	private static Map<String, String> userIdMap = new LinkedHashMap<String, String>();

	@OnMessage
	public void onMessage(Session session, String msg) {
		JsonObject data = Json.createReader(new StringReader(msg)).readObject();
		checkNewConnection(session, data);
	}

	@OnOpen
	public void open(Session session) {
		queue.put(session.getId(), session);
		System.out.println("New session opened: " + session.getId());
	}

	@OnError
	public void error(Session session, Throwable t) {
		queue.remove(session.getId());
		System.err.println("Error on session " + session.getId());

	}

	@OnClose
	public void closedConnection(Session session) {
		userMap.remove(userIdMap.get(session.getId()));
		queue.remove(session.getId());
		userIdMap.remove(session.getId());
		System.out.println("session closed: " + session.getId());
	}

	private void checkNewConnection(Session session, JsonObject data) {
		if (data.get("connect") != null) {
			if (userMap.get(data.getString("user")) == null) {
				broadcastMessage(data.getString("user") + " is Online");
				userMap.put(data.getString("user"), session.getId());
				userIdMap.put(session.getId(), data.getString("user"));
			} else {
				try {
					Session oldSession = queue.get(userMap.get(data.getString("user")));
					oldSession.getBasicRemote().sendText("Another Session Opened : Connection Closed");
					queue.remove(oldSession.getId());
					userMap.put(data.getString("user"), session.getId());
					userIdMap.put(session.getId(), data.getString("user"));
					oldSession.close();
				} catch (IOException e) {
				}
			}
		} else {
			broadcast(data);
		}
	}

	private static void broadcastMessage(String msg) {
		Session session;
		try {
			for (Entry<String, Session> pair : queue.entrySet()) {
				session = pair.getValue();
				if (!session.isOpen()) {
					System.err.println("Closed session: " + session.getId());
					queue.remove(session.getId());
				} else {
					session.getBasicRemote().sendText(msg);
				}
			}
			System.out.println("Braodcast Message : " + msg);
			System.out.println("Send to " + queue.size() + " clients");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void broadcast(Object data) {
		JsonObject bcData = (JsonObject) data;
		Session session;
		try {
			for (Entry<String, Session> pair : queue.entrySet()) {
				session = pair.getValue();
				if (!session.isOpen()) {
					System.err.println("Closed session: " + session.getId());
					queue.remove(session.getId());
				} else {
					session.getBasicRemote().sendText(bcData.toString());
				}
			}
			System.out.println("Braodcast Message : " + bcData);
			System.out.println("Send to " + queue.size() + " clients");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
