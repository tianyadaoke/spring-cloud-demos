package org.zb.hotelmysql.constants;

public class MqConstants {
    /**
     * 交换机
     */
    public final  static  String HOTEL_EXCHANGE = "hotel.topic";
    /**
     * 监听新增和修改修改的队列
     */
    public final  static  String HOTEL_INSERT_QUEUE = "hotel.insert.queue";
    /**
     * 监听删除的队列
     */
    public final  static  String HOTEL_DELETE_QUEUE = "hotel.delete.queue";
    /**
     * 新增或者修改RoutingKey
     */
    public final  static  String HOTEL_INSERT_KEY = "hotel.insert";
    /**
     * 删除RoutingKey
     */
    public final  static  String HOTEL_DELETE_KEY = "hotel.delete";

}
