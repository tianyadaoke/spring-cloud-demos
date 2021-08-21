package org.zb.hotelmysql.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.zb.hotelmysql.pojo.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
