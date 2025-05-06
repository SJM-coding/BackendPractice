package com.rookies3.MySpringBootLab.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DevConfig {

    @Bean
    public MyEnvironment myEnvironment() {
        return new MyEnvironment("개발환경");
    }
}