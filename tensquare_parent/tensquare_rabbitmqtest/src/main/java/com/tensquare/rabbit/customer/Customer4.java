package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue2")
public class Customer4 {

    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println("queue2: " + msg);
    }


}
