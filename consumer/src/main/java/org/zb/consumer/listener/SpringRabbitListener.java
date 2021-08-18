package org.zb.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class SpringRabbitListener {
//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg){
//        System.out.println("消费者接收到simple queue的消息是："+msg);
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkqueue(String msg) throws InterruptedException {
        System.out.println("消费者1...接收到simple queue的消息是："+msg+"---"+ LocalTime.now());
        Thread.sleep(50);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkqueue2(String msg) throws InterruptedException {
        System.err.println("消费者2...接收到simple queue的消息是："+msg+"---"+ LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.err.println("消费者1...接收到Fanout Queue1的消息是："+msg);
    }
    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2...接收到Fanout Queue2的消息是："+msg);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.err.println("消费者1...接收到Direct Queue1的消息是："+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenDirectQueue2(String msg){
        System.err.println("消费者2...接收到Direct Queue2的消息是："+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg){
        System.err.println("消费者1...接收到Topic Queue1的消息是："+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg){
        System.err.println("消费者2...接收到Topic Queue2的消息是："+msg);
    }
    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String,Object> map){
        System.out.println("接收到的object的消息"+map);
    }

}
