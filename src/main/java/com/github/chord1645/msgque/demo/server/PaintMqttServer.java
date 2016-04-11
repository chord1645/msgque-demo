package com.github.chord1645.msgque.demo.server;

import com.github.chord1645.msgque.demo.Topics;
import com.google.common.collect.Lists;
import io.moquette.BrokerConstants;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.moquette.server.Server;
import io.moquette.server.config.MemoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class PaintMqttServer extends Server {

    private static final Logger logger = LoggerFactory.getLogger(PaintMqttServer.class);


    public static void main(String[] args) throws IOException {

        Properties cfg = new Properties();
        cfg.put(BrokerConstants.HOST_PROPERTY_NAME, "localhost");
        cfg.put(BrokerConstants.PORT_PROPERTY_NAME, "9999");
        final PaintMqttServer server = new PaintMqttServer();
//        final Properties configProps = IntegrationUtils.prepareTestPropeties();
        class StartGameListener extends AbstractInterceptHandler {

            @Override
            public void onPublish(InterceptPublishMessage msg) {
//            logger.info("onPublish {} {}", msg.getClientID(), msg.getTopicName());
                if (Topics.START.equalsIgnoreCase(msg.getTopicName())) {
                }
            }
        }
        class JoinListener extends AbstractInterceptHandler {
            @Override
            public void onPublish(InterceptPublishMessage msg) {
//            logger.info("JoinListener {} {}", msg.getClientID(), msg.getTopicName());
                if (Topics.JOIN.equalsIgnoreCase(msg.getTopicName())) {
                }
            }
        }

//        server.startServer(new MemoryConfig(cfg), Lists.newArrayList(new StartGameListener(), new JoinListener()));
        server.startServer(new MemoryConfig(cfg));
        System.out.println("Server started, version 0.8");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                server.stopServer();
            }
        });

    }


}