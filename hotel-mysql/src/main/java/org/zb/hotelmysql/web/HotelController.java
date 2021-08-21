package org.zb.hotelmysql.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.zb.hotelmysql.pojo.Hotel;
import org.zb.hotelmysql.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return hotelService.findById(id);
    }

    @GetMapping("/all")
    public Page<Hotel> hotel(int size,int page){
        PageRequest pageRequest = PageRequest.of(page,size);
        return hotelService.findAll(pageRequest);
    }

    @PostMapping
    public void save(@RequestBody  Hotel hotel) {
       hotelService.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        hotelService.deletById(id);
    }

    @PutMapping
    public void update(Hotel hotel) {
        hotelService.save(hotel);
    }

}
