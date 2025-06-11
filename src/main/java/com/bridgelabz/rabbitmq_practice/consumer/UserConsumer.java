package com.bridgelabz.rabbitmq_practice.consumer;

import com.bridgelabz.rabbitmq_practice.config.MessagingConfig;
import com.bridgelabz.rabbitmq_practice.entity.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserConsumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        log.info("Message received from queue: "+orderStatus);
    }
}
