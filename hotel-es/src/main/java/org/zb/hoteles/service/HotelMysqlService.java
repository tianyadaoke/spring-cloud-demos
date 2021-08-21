package org.zb.hoteles.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zb.hoteles.pojo.Hotel;

public interface HotelMysqlService extends JpaRepository<Hotel,Long> {
}
