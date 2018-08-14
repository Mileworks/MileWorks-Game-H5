package com.mileworks.game.h5.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
  
@Component  
public class MSGameWsServerRunner implements CommandLineRunner {  
    private final MSWsServer server;
    
    @Autowired  
    public MSGameWsServerRunner(MSWsServer server) {  
        this.server = server;  
    }
    
    public void run(String... args) throws Exception { 
        server.start();  
    }  
}  