package org.zb.hotelesdemo.service;

import org.zb.hotelesdemo.pojo.PageResult;
import org.zb.hotelesdemo.pojo.RequestParams;

public interface IHotelService {
    PageResult search(RequestParams params);
}
