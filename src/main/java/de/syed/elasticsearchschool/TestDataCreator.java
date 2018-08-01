package de.syed.elasticsearchschool;

import org.elasticsearch.client.RestHighLevelClient;

public class TestDataCreator {
    public static void createTestData() {
        RestHighLevelClient client = ElasticClientWrapper.getClient();
    }
}
