package org.zb.hoteles.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zb.hoteles.constants.MqConstants;
import org.zb.hoteles.service.HotelEsService;

@Component
public class HotelRabbitListener {
    @Autowired
    HotelEsService hotelEsService;

    @RabbitListener(queues = MqConstants.HOTEL_INSERT_QUEUE)
    public void listenHotelInsertOrUpdate(Long id){
        hotelEsService.insertById(id);
    }
    @RabbitListener(queues = MqConstants.HOTEL_DELETE_QUEUE)
    public void listenHotelDelete(Long id){
        hotelEsService.deleteById(id);
    }
}
