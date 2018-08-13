package com.mileworks.game.h5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.server.ServerGroupContext;
import org.tio.websocket.server.WsServerStarter;

import com.mileworks.game.h5.websocket.ShowcaseServerAioListener;
import com.mileworks.game.h5.websocket.ShowcaseServerConfig;
import com.mileworks.game.h5.websocket.ShowcaseWsMsgHandler;

@SpringBootApplication
public class MileWorksGameH5Application {

	private WsServerStarter wsServerStarter;
	private ServerGroupContext serverGroupContext;
	
	public MileWorksGameH5Application(int port, ShowcaseWsMsgHandler wsMsgHandler) throws Exception {
		wsServerStarter = new WsServerStarter(port, wsMsgHandler);

		serverGroupContext = wsServerStarter.getServerGroupContext();
		serverGroupContext.setName(ShowcaseServerConfig.PROTOCOL_NAME);
		serverGroupContext.setServerAioListener(ShowcaseServerAioListener.me);

		//设置ip监控
//		serverGroupContext.setIpStatListener(ShowcaseIpStatListener.me);
		//设置ip统计时间段
		serverGroupContext.ipStats.addDurations(ShowcaseServerConfig.IpStatDuration.IPSTAT_DURATIONS);
		
		//设置心跳超时时间
		serverGroupContext.setHeartbeatTimeout(ShowcaseServerConfig.HEARTBEAT_TIMEOUT);
		//如果你希望通过wss来访问，就加上下面的代码吧，不过首先你得有SSL证书（证书必须和域名相匹配，否则可能访问不了ssl）
//		String keyStoreFile = "classpath:config/ssl/keystore.jks";
//		String trustStoreFile = "classpath:config/ssl/keystore.jks";
//		String keyStorePwd = "214323428310224";
//		serverGroupContext.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
		
	}
	
	public static void main(String[] args) throws Exception{
		MileWorksGameH5Application appStarter = new MileWorksGameH5Application(ShowcaseServerConfig.SERVER_PORT, ShowcaseWsMsgHandler.me);
		appStarter.wsServerStarter.start();
		
		SpringApplication.run(MileWorksGameH5Application.class, args);
	}
}
