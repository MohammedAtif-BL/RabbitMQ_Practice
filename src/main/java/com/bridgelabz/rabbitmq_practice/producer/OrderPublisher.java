package com.bridgelabz.rabbitmq_practice.producer;

import com.bridgelabz.rabbitmq_practice.config.MessagingConfig;
import com.bridgelabz.rabbitmq_practice.entity.Order;
import com.bridgelabz.rabbitmq_practice.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderPublisher {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order,"PROCESS","order placed successfully in "+restaurantName);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,orderStatus);
        return "Success";
    }
}
