package org.zb.hotelmysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zb.hotelmysql.dao.HotelRepository;
import org.zb.hotelmysql.pojo.Hotel;

@Component
public class HotelServiceImpl implements HotelService{
    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void deletById(Long id) {
        hotelRepository.deleteById(id);
    }
}
