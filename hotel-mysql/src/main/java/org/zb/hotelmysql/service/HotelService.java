package org.zb.hotelmysql.service;

import org.zb.hotelmysql.pojo.Hotel;

public interface HotelService {
    Hotel findById(Long id);

    void save(Hotel hotel);

    void deletById(Long id);
}
