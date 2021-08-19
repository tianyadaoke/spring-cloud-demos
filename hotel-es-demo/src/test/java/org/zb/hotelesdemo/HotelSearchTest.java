package org.zb.hotelesdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.zb.hotelesdemo.constants.HotelConstants;
import org.zb.hotelesdemo.pojo.HotelDoc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HotelSearchTest {
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
    void matchAllTest() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void matchTest() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchQuery("all","如家"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void booleanTest() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("city","北京"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(500));
        request.source().query(boolQueryBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    private void handleResponse(SearchResponse response) throws com.fasterxml.jackson.core.JsonProcessingException {
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到数据 = " + total);
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = new ObjectMapper().readValue(json, HotelDoc.class);
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(!CollectionUtils.isEmpty(highlightFields)){
                HighlightField highlightField = highlightFields.get("name");
                if(highlightField!=null){
                    String name = highlightField.getFragments()[0].toString();
                    hotelDoc.setName(name);
                }
            }
            System.out.println(hotelDoc);
        }
    }
    @Test
    void pageAndSortTest() throws IOException {
        int page=1,size=5;
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchAllQuery());
        request.source().sort("price", SortOrder.ASC);
        request.source().from((page-1)*size).size(size);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void highLightTest() throws IOException {
        int page=1,size=5;
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchQuery("all","如家"));
        request.source().highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void aggregationTest() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().size(0);
        request.source().aggregation(AggregationBuilders.terms("brandAgg")
            .field("brand")
            .size(10));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        Terms brandTerms = aggregations.get("brandAgg");
        List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
            System.out.println(key);
        }
    }

}
