package org.zb.hotelmysql.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zb.hotelmysql.pojo.Hotel;
import org.zb.hotelmysql.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id){
        return hotelService.findById(id);
    }
    @PostMapping
    public void save(@RequestBody  Hotel hotel){
        hotelService.save(hotel);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        hotelService.deletById(id);
    }
    @PutMapping
    public void update(Hotel hotel){
        hotelService.save(hotel);
    }

}
