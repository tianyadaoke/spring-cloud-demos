package org.zb.hotelesdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zb.hotelesdemo.dao.HotelRepository;
import org.zb.hotelesdemo.pojo.Hotel;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @GetMapping("/hotels")
    public List<Hotel> getAll(){
        return hotelRepository.findAll();
    }
}
