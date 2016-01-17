package com.exmyth.attendance.websocket;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class ScanWebSocketServerConfig implements ServerApplicationConfig {

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		Set<Class<?>> result = new HashSet<Class<?>>();
		for(Class<?> clazz :scanned){
			if(clazz.getPackage().getName().startsWith("com.exmyth.attendance.websocket.")){
				System.out.println("find end poin:"+clazz.getName());
				result.add(clazz);
			}
		}
		return result;
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();
		/*
		if(scanned.contains(EchoWsChatServer.class)){
			result.add(ServerEndpointConfig.Builder.create(EchoWsChatServer.class, "/echo").build());
		}
		*/
		return result;
	}

}
