package com.study.controller;

import com.study.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SendMessageController
 * @Author Yutian Hui
 * @date 2020.07.22 18:13
 */
@RestController
@RequestMapping("/send")
public class SendMessageController {

    @Autowired
    private MessageProvider messageProvider;

    @GetMapping("/hello")
    public String sendMessage(){
        return messageProvider.send();
    }

}
