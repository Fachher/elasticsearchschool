package de.syed.elasticsearchschool;

import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.text.MessageFormat;

public class ShipmentTestDataCreator extends ClientObtainer{

    public static final BasicHeader APPLICATION_JSON = new BasicHeader("Content-Type", "application/json");

    public static void createTestData() throws IOException {
        dropIndex();
        createIndex();
        persistDocuments();
    }

    private static void persistDocuments() throws IOException {
//        MessageFormat messageFormat = new MessageFormat("{\"shipmentId\":{0},\"trackingNumber\":\"tn{0}\"}");

        highLevelCall((client) -> {
            try {
                for (int i = 0; i < 100; i++) {
                    IndexRequest indexRequest = new IndexRequest("shipment", "doc", ""+i);
                    indexRequest.source("{\"shipmentId\":1,\"trackingNumber\":\"tn1\"}", XContentType.JSON);
                    client.index(indexRequest, APPLICATION_JSON);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void dropIndex() throws IOException {
        indexCall((client) -> {
            try {
                client.delete(new DeleteIndexRequest("shipment"), APPLICATION_JSON);
                System.out.println("Index shipment deleted");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void createIndex() throws IOException {
        indexCall((client) -> {
            try {
                client.create(new CreateIndexRequest("shipment"), APPLICATION_JSON);
                System.out.println("Index shipment created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        createTestData();
        ElasticInstanceInfo.getAllIndices();
    }

}
