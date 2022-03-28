package com.matthias.springcloud.controller;

import com.matthias.springcloud.service.IMassageProvider;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class SendMassageController {

    @Resource
    private IMassageProvider iMassageProvider;

    @GetMapping(value = "/sendMassage")
    public String sendMassage() {
        return iMassageProvider.send();
    }

}
