package org.zb.hotelmysql.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.zb.hotelmysql.constants.MqConstants;
import org.zb.hotelmysql.dao.HotelRepository;
import org.zb.hotelmysql.pojo.Hotel;


@Component
public class HotelServiceImpl implements HotelService{
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE,MqConstants.HOTEL_INSERT_KEY,hotel.getId());
    }

    @Override
    public void deletById(Long id) {
        hotelRepository.deleteById(id);
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE,MqConstants.HOTEL_DELETE_KEY,id);
    }

    @Override
    public Page<Hotel> findAll(PageRequest pageRequest) {
        return hotelRepository.findAll(pageRequest);
    }
}
