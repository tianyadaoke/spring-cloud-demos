package org.zb.hotelesdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zb.hotelesdemo.service.IHotelService;

import java.util.List;
import java.util.Map;

@SpringBootTest
class HotelEsDemoApplicationTests {
    @Autowired
    IHotelService hotelService;

    @Test
    void filterTest() {
//        Map<String, List<String>> filters = hotelService.filters();
//        System.out.println(filters);
    }
}
