package com.mileworks.game.h5.websocket;

import java.io.IOException;

import org.tio.server.ServerGroupContext;
import org.tio.websocket.server.WsServerStarter;

/**
 * websocket 相关配置不在main方法中执行
 * @author long-laptop
 * 2018.8.14
 */ 

public class MSWsServer {

	private ServerGroupContext serverGroupContext;
	private WsServerStarter wsServerStarter;

	public MSWsServer(int port, MSGameMsgHandler wsMsgHandler) throws IOException {
		wsServerStarter = new WsServerStarter(port, wsMsgHandler);
		serverGroupContext = wsServerStarter.getServerGroupContext();
		
		
		serverGroupContext = wsServerStarter.getServerGroupContext();
		serverGroupContext.setName(MSGameServerConfig.PROTOCOL_NAME);
		serverGroupContext.setServerAioListener(MSGameServerAioListener.me);

		//设置ip监控
		serverGroupContext.setIpStatListener(MSGameIpStatListener.me);
		//设置ip统计时间段
		serverGroupContext.ipStats.addDurations(MSGameServerConfig.IpStatDuration.IPSTAT_DURATIONS);
		
		//设置心跳超时时间
		serverGroupContext.setHeartbeatTimeout(MSGameServerConfig.HEARTBEAT_TIMEOUT);
		//如果你希望通过wss来访问，就加上下面的代码吧，不过首先你得有SSL证书（证书必须和域名相匹配，否则可能访问不了ssl）
//		String keyStoreFile = "classpath:config/ssl/keystore.jks";
//		String trustStoreFile = "classpath:config/ssl/keystore.jks";
//		String keyStorePwd = "214323428310224";
//		serverGroupContext.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
		
	}

	public ServerGroupContext getServerGroupContext() {
		return serverGroupContext;
	}

	public WsServerStarter getWsServerStarter() {
		return wsServerStarter;
	}

	public void start() throws IOException {
		wsServerStarter.start();
	}
	
}
