package com.study.service.impl;

import com.study.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName MessageProviderImpl
 * @Author Yutian Hui
 * @date 2020.07.22 18:03
 */
// 定义消息的推送管道
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    // 注入信息发送管道
    @Resource(name = "output") //自动注入bean
    private MessageChannel messageChannel;

    @Override
    public String send() {
        // 创建一个流水号并发送
        String s = UUID.randomUUID().toString();
        log.info("发送的UUID:" + s);
        messageChannel.send(MessageBuilder.withPayload(s)
                .setHeader("name","leibohui")
                .build());
        return "successful";
    }
}
