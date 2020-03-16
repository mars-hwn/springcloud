package com.alonginfo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Author:hwn
 * Date:2020-03-13 10:50
 * Description:<描述>
 */
@EnableBinding(Sink.class)
public class ConsumerController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @StreamListener(target = Sink.INPUT)
    public void consume(String message) {
        logger.info("recieved a string message : " + message);
    }

     @StreamListener(target = Sink.INPUT, condition = "headers['type']=='chat'")
        public void handle(@Payload ChatMessage message) {
            final DateTimeFormatter df = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .withZone(ZoneId.systemDefault());
            final String time = df.format(Instant.ofEpochMilli(message.getTime()));
            logger.info("recieved a complex message : [{}]: {}", time, message.getContents());
    }

}


