package com.alonginfo.service;

import com.alonginfo.entiy.Message;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author:hwn
 * Date:2020-03-11 17:09
 * Description:<描述>
 */
@Service
@EnableBinding(Source.class)
public class SendServiceImpl implements SendService {

    @Resource
    private Source source;

    @Override
    public void sendMsg(Message msg) {
        source.output().send(MessageBuilder.withPayload(msg).build());

    }
}

