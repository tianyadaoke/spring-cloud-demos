package org.zb.hoteles.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zb.hoteles.pojo.Hotel;
import org.zb.hoteles.pojo.HotelDoc;

import java.io.IOException;
import java.util.Optional;

@Component
public class HotelEsService {
    @Autowired
    HotelMysqlService hotelMysqlService;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    public void deleteById(Long id) {
        try {
            DeleteRequest request = new DeleteRequest("hotel",id.toString());
            restHighLevelClient.delete(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertById(Long id) {
        try {
            Optional<Hotel> hotelOptional = hotelMysqlService.findById(id);
            if(!hotelOptional.isPresent()) return;
            Hotel hotel = hotelOptional.get();
            HotelDoc hotelDoc = new HotelDoc(hotel);
            IndexRequest request = new IndexRequest("hotel").id(hotel.getId().toString());
            request.source(new ObjectMapper().writeValueAsString(hotelDoc), XContentType.JSON);
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
