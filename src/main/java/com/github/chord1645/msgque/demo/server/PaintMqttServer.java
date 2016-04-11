package com.github.chord1645.msgque.demo.server;

import com.github.chord1645.msgque.demo.Topics;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import io.moquette.BrokerConstants;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptDisconnectMessage;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.moquette.server.Server;
import io.moquette.server.config.MemoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PaintMqttServer extends Server {

    private static final Logger logger = LoggerFactory.getLogger(PaintMqttServer.class);
    //    Map<String, List<String>> room = new HashMap<>();
    static Multimap<String, String> room = ArrayListMultimap.create();

    public static void main(String[] args) throws IOException {

        Properties cfg = new Properties();
        cfg.put(BrokerConstants.HOST_PROPERTY_NAME, "localhost");
        cfg.put(BrokerConstants.PORT_PROPERTY_NAME, "9999");
        final PaintMqttServer server = new PaintMqttServer();
//        final Properties configProps = IntegrationUtils.prepareTestPropeties();
        class JoinListener extends AbstractInterceptHandler {
            @Override
            public void onPublish(InterceptPublishMessage msg) {
                if (Topics.S_JOIN.equalsIgnoreCase(msg.getTopicName())) {
                    try {
                        String roomId = new String(msg.getPayload().array(), "utf-8");
                        room.put(roomId, msg.getClientID());
                        logger.info("user [{}] join room [{}] players:[{}]", msg.getClientID(), roomId,room);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
        class QuitListener extends AbstractInterceptHandler {
            @Override
            public void onPublish(InterceptPublishMessage msg) {
                if (Topics.S_QUIT.equalsIgnoreCase(msg.getTopicName())) {
                }
            }
        }
        class AnswerListener extends AbstractInterceptHandler {
            @Override
            public void onPublish(InterceptPublishMessage msg) {
                if (Topics.S_ANSWER.equalsIgnoreCase(msg.getTopicName())) {
                }
            }
        }
        class ReadyListener extends AbstractInterceptHandler {
            @Override
            public void onPublish(InterceptPublishMessage msg) {
                if (Topics.S_READY.equalsIgnoreCase(msg.getTopicName())) {
                }
            }
        }
        server.startServer(new MemoryConfig(cfg), Lists.newArrayList(new JoinListener()));
//        server.startServer(new MemoryConfig(cfg));
        System.out.println("Server started, version 0.8");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                server.stopServer();
            }
        });

    }


}