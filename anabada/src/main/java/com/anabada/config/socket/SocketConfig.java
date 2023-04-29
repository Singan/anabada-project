package com.anabada.config.socket;

import org.springframework.context.annotation.Bean;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
public class SocketConfig {

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8082);

        SocketIOServer server = new SocketIOServer(config);
        return server;
    }
}
