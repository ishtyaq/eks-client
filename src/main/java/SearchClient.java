import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SearchClient {
    // key: log
    private  final String INDEX_NAME1 = "logstash-2023.02.22";
    private final String INDEX_NAME = "myindex";
    RestHighLevelClient client;

    public SearchClient() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        this.client =  new RestHighLevelClient(
                RestClient.builder(new HttpHost(bundle.getString("elasticsearch.server"), Integer.parseInt(bundle.getString("elasticsearch.port")), "http")));
    }
    public void SearchText(String searchText) throws IOException {

       // Create search request
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("text", searchText));
        searchRequest.source(searchSourceBuilder);

        // Execute search request
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // Log response
        for (SearchHit hit : searchResponse.getHits()) {
            //log.info(hit.getSourceAsString());
            System.out.println(hit.getSourceAsString());
        }

    }

    public void AddText(String inputText) throws IOException {


        Map<String, Object> data = new HashMap<>();
        data.put("text", inputText);
        // myindex
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .id("1")
                .source(data);

        client.index(indexRequest, RequestOptions.DEFAULT);

    }
    public void closeClient() throws IOException {

        client.close();

    }
}

