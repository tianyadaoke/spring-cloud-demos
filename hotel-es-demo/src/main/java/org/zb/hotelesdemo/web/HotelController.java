package org.zb.hotelesdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zb.hotelesdemo.dao.HotelRepository;
import org.zb.hotelesdemo.pojo.Hotel;
import org.zb.hotelesdemo.pojo.PageResult;
import org.zb.hotelesdemo.pojo.RequestParams;
import org.zb.hotelesdemo.service.IHotelService;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    IHotelService hotelService;
    @GetMapping("/hotels")
    public List<Hotel> getAll(){
        return hotelRepository.findAll();
    }
    @PostMapping("/list")
    public PageResult search(@RequestBody RequestParams params){
        return hotelService.search(params);
    }
}
