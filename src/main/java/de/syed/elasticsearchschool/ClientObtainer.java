package de.syed.elasticsearchschool;

import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.function.Consumer;

public class ClientObtainer {
    protected static void highLevelCall(Consumer<RestHighLevelClient> consumer) throws IOException {
        try (RestHighLevelClient client = ElasticClientWrapper.getClient()) {
            consumer.accept(client);
        }
    }

    protected static void lowLevelCall(Consumer<RestClient> consumer) throws IOException {
        try (RestHighLevelClient client = ElasticClientWrapper.getClient();
             RestClient lowLevelClient = client.getLowLevelClient();){
            consumer.accept(lowLevelClient);
        }
    }

    protected static void indexCall(Consumer<IndicesClient> consumer) throws IOException {
        try(RestHighLevelClient client = ElasticClientWrapper.getClient()){
            consumer.accept(client.indices());
        }
    }
}
