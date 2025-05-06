package com.rookies3.MySpringBootLab.runner;

import com.rookies3.MySpringBootLab.Property.MyPropProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MyPropRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyPropRunner.class);


    @Autowired
    private MyPropProperties properties;

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("username = {}", properties.getUsername());
        logger.debug("port = {}", properties.getPort());
    }
}
