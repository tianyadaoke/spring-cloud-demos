package org.zb.hoteles.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.zb.hoteles.constants.MqConstants;

@Component
public class HotelRabbitListener {
    @RabbitListener(queues = MqConstants.HOTEL_INSERT_QUEUE)
    public void insertQueueAction(String msg){
        System.out.println("收到"+MqConstants.HOTEL_INSERT_QUEUE+"的消息:"+msg);
    }
}
