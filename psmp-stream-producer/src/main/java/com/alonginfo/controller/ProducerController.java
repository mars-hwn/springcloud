package com.alonginfo.controller;


import com.alonginfo.entiy.Message;
import com.alonginfo.pageConfig.Producer;
import com.alonginfo.service.SendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;


/**
 * Author:hwn
 * Date:2020-03-11 11:10
 * Description:<描述>
 */
@RestController
public class ProducerController {

    //@Resource
    private SendServiceImpl sendService;

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/sendMessage/string", method = RequestMethod.POST)
    public String publishMessageString(@RequestBody String payload) {
        producer.getMysource().output().send(MessageBuilder.withPayload(payload).setHeader("type", "string").build());
        return "success";
    }

    @PostMapping("/sendMsg")
    public void send(Message message) {
        sendService.sendMsg(message);
    }
}


