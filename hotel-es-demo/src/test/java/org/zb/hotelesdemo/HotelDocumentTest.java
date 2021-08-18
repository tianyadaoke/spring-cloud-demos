package org.zb.hotelesdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zb.hotelesdemo.constants.HotelConstants;
import org.zb.hotelesdemo.dao.HotelRepository;
import org.zb.hotelesdemo.pojo.Hotel;
import org.zb.hotelesdemo.pojo.HotelDoc;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class HotelDocumentTest {
    @Autowired
    HotelRepository hotelRepository;

    private RestHighLevelClient client;

    @BeforeEach
    void setUp(){
        this.client=new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
    }

    @AfterEach
     void afterEach() throws IOException {
        this.client.close();
    }

    @Test
    void addDocumentTest() throws IOException {
        Hotel hotel = hotelRepository.findById(61083L).get();
        HotelDoc hotelDoc = new HotelDoc(hotel);
        IndexRequest indexRequest = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        indexRequest.source(new ObjectMapper().writeValueAsString(hotelDoc),XContentType.JSON);
        client.index(indexRequest,RequestOptions.DEFAULT);
    }

    @Test
    void getDocumentTest() throws IOException {
        GetRequest getRequest = new GetRequest("hotel","61083");
        GetResponse res = client.get(getRequest, RequestOptions.DEFAULT);
        String source = res.getSourceAsString();
        HotelDoc hotelDoc = new ObjectMapper().readValue(source, HotelDoc.class);
        System.out.println(hotelDoc);
    }
    @Test
    void updateDocumentTest() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("hotel", "61083");
        updateRequest.doc(
                "price","950"
        );
        client.update(updateRequest,RequestOptions.DEFAULT);
    }

    @Test
    void deleteDocumentTest() throws IOException {
        DeleteRequest request = new DeleteRequest("hotel", "61083");
        client.delete(request,RequestOptions.DEFAULT);
    }
    @Test
    void bulkRequestTest() throws IOException {
        List<Hotel> hotels = hotelRepository.findAll();
        BulkRequest request = new BulkRequest();
        for (Hotel hotel : hotels) {
            HotelDoc hotelDoc = new HotelDoc(hotel);
            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source(new ObjectMapper().writeValueAsString(hotelDoc),XContentType.JSON));
        }
        client.bulk(request,RequestOptions.DEFAULT);
    }
}
