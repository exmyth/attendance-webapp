package com.exmyth.attendance.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Repository;
@Repository
@ServerEndpoint(value="/echo")
public class EchoController {
	@OnMessage
	public void echoTextMessage(Session session, String msg, boolean last) {
		try {
			if (session.isOpen()) {
				System.out.println("received from client message = " + msg);
				session.getBasicRemote().sendText(msg, last);
			}
		} catch (IOException e) {
			try {
				session.close();
			} catch (IOException e1) {
			}
		}
	}

	@OnOpen
	public void openConn(Session session) throws IOException {
		session.getBasicRemote().sendText("hello web socket"); // means open it
	}
	
	@OnMessage
	public void echoBinaryMessage(Session session, ByteBuffer bb, boolean last) {
		System.out.println("send binary message...");
		try {
			if (session.isOpen()) {
				System.out.println("byte buffer lenghth : " + bb.array().length);
				System.out.println("byte buffer content: " + ((bb.array()[0]) & 0xff));
				System.out.println("byte buffer content: " + ((bb.array()[1]) & 0xff));
				System.out.println("byte buffer content: " + ((bb.array()[2]) & 0xff));
				session.getBasicRemote().sendBinary(bb, last);
			}
		} catch (IOException e) {
			try {
				session.close();
			} catch (IOException e1) {
				// Ignore
			}
		}
	}
}
