package com.myweb.jnlp;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class SocketConnect {
	private final String uri = "ws://localhost:8080/testSpring/ws/civilIdReader";
	private Session session;

	public SocketConnect() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI(uri));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("New session opened: " + session.getId());
		this.session = session;
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received msg: " + message);
	}

	public void sendMessage(String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException ex) {

		}
	}
}
