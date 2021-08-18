package org.zb.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SpringSimpleMessageTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    void sendSimpleMessage(){
        String queueName="simple.queue";
        String message = "hello rabbit mq ";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    void sendSimpleMessage2WorkQueue() throws InterruptedException {
        String queueName="simple.queue";
        String message = "hello rabbit mq ";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);
        }
    }

    @Test
    void sendFanoutExchange(){
        String exchangeName="itcast.fanout";
        String message = "hello ,every one";
        rabbitTemplate.convertAndSend(exchangeName,"",message);
    }

    @Test
    void sendDirectExchange(){
        String exchangeName="itcast.direct";
        String message = "hello ,blue";
        rabbitTemplate.convertAndSend(exchangeName,"blue",message);
    }


    @Test
    void sendTopicExchange(){
        String exchangeName="itcast.topic";
        String message = "hello topic ";
        rabbitTemplate.convertAndSend(exchangeName,"japan.news",message);
    }

    @Test
    void sendObjectQueue(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","留言");
        map.put("age",21);
        rabbitTemplate.convertAndSend("object.queue",map);
    }
}
