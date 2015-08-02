package io.codechobostudy.qna.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO profile으로 비활성화 필요.
@Configuration
public class WebConfiguration {

    @Bean
    public Server h2Server() {
        Server server = new Server();
        try {
            server.runTool("-tcp");
            server.runTool("-tcpAllowOthers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }
}
