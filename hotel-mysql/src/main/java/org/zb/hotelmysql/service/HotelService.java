package org.zb.hotelmysql.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.zb.hotelmysql.pojo.Hotel;

import java.util.List;

public interface HotelService {
    Hotel findById(Long id);

    void save(Hotel hotel);

    void deletById(Long id);

    Page<Hotel> findAll(PageRequest pageRequest);
}
