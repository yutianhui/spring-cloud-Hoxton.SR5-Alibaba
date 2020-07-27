package com.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @ClassName MessageReceiveController
 * @Author Yutian Hui
 * @date 2020.07.22 21:08
 */
@Slf4j
@EnableBinding(Sink.class)
public class MessageReceiveController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        log.info("端口号为:" + serverPort + "的消费者,接收到的消息 ==> " + message.getPayload());
    }


}
