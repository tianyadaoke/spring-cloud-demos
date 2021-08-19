package org.zb.hotelesdemo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zb.hotelesdemo.pojo.HotelDoc;
import org.zb.hotelesdemo.pojo.PageResult;
import org.zb.hotelesdemo.pojo.RequestParams;
import org.zb.hotelesdemo.service.IHotelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HotelServiceImpl implements IHotelService {
    @Autowired
    RestHighLevelClient client;
    @Override
    public PageResult search(RequestParams params) {
        try {
            // 1.准备request
            SearchRequest request = new SearchRequest("hotel");
            // 2.准备dsl
            // 2.1 query
            buildBasicQuery(params, request);

            // 2.2 分页
            int page = params.getPage();
            int size = params.getSize();
            request.source().from((page-1)*size).size(size);
            // 3.发送请求得到相应
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4.解析相应
            return handleResponse(response);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

    private void buildBasicQuery(RequestParams params, SearchRequest request) {
        // 构建boolquery
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 关键字搜索
        String key = params.getKey();
        if(key==null||"".equals(key)){
            boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        } else{
            boolQueryBuilder.must(QueryBuilders.matchQuery("all",key));
        }
        // 条件过滤
        // 城市条件
        String city = params.getCity();
        if(city!=null&&!"".equals(city)){
            boolQueryBuilder.filter(QueryBuilders.termQuery("city",city));
        }
        //品牌条件
        String brand = params.getBrand();
        if(brand!=null&&!"".equals(brand)){
            boolQueryBuilder.filter(QueryBuilders.termQuery("brand",brand));
        }
        //星级条件
        String starName = params.getStarName();
        if(starName!=null&&!"".equals(starName)){
            boolQueryBuilder.filter(QueryBuilders.termQuery("starName",starName));
        }
        //价格条件
        Integer minPrice = params.getMinPrice();
        Integer maxPrice = params.getMaxPrice();
        if(minPrice!=null&&maxPrice!=null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        }

        request.source().query(boolQueryBuilder);
    }

    private PageResult handleResponse(SearchResponse response) throws com.fasterxml.jackson.core.JsonProcessingException {
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到数据 = " + total);
        SearchHit[] hits = searchHits.getHits();
        List<HotelDoc> hotels = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = new ObjectMapper().readValue(json, HotelDoc.class);
            hotels.add(hotelDoc);
        }
        return  new PageResult(total,hotels);
    }
}
