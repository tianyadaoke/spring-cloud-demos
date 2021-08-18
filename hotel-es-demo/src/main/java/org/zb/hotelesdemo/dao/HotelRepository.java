package org.zb.hotelesdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zb.hotelesdemo.pojo.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
