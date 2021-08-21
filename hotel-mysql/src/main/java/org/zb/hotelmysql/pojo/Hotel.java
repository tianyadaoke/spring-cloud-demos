package org.zb.hotelmysql.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    private String starName;
    private String business;
    private String longitude;
    private String latitude;
    private String pic;
}
