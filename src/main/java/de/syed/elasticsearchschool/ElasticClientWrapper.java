package de.syed.elasticsearchschool;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticClientWrapper {
    public static RestHighLevelClient getClient(){
        final HttpHost host = new HttpHost("localhost", 9200);
        return new RestHighLevelClient(RestClient.builder(host));
    }
}
