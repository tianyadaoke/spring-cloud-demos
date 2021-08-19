package org.zb.hotelesdemo;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelEsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelEsDemoApplication.class, args);
    }

    @Bean
    public RestHighLevelClient client(){
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
    }

}
