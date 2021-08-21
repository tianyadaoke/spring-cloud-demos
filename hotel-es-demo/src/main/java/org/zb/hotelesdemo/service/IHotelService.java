package org.zb.hotelesdemo.service;

import org.zb.hotelesdemo.pojo.PageResult;
import org.zb.hotelesdemo.pojo.RequestParams;

import java.util.List;
import java.util.Map;

public interface IHotelService {
    PageResult search(RequestParams params);

    Map<String, List<String>> filters(RequestParams params);
}
