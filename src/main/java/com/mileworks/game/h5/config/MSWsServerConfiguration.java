package com.mileworks.game.h5.config;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mileworks.game.h5.websocket.MSGameMsgHandler;
import com.mileworks.game.h5.websocket.MSWsServer;


@Configuration
public class MSWsServerConfiguration {
	@Value("${mk.im.server.host}")
	private String host;

	@Value("${mk.im.server.port}")
	private Integer port;

	@Value("${mk.im.server.threads}")
	private String threads;
	
	private MSGameMsgHandler handler = new MSGameMsgHandler();

	@Bean(name = "webimport")
	public Integer getWebIMPort() {
		return port;
	}

	@Bean
	public MSWsServer socketIOServer() throws NoSuchAlgorithmException, IOException {
		MSWsServer server = new MSWsServer(port, handler);
		handler.setServer(server);
		return server;
	}
}