package de.syed.elasticsearchschool;

import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.Response;

import java.io.*;

public class ElasticInstanceInfo extends ClientObtainer{

    public static final BasicHeader APPLICATION_JSON = new BasicHeader("Content-Type", "application/json");

    static void getInfrastructureInfo() throws IOException {

        highLevelCall((client) -> {
            try {
                MainResponse info = client.info(APPLICATION_JSON);
                System.out.printf("clustername: %s\n", info.getClusterName());
                System.out.printf("clusteruuid: %s\n", info.getClusterUuid());
                System.out.printf("nodename: %s\n", info.getNodeName());
                System.out.printf("version: %d\n", info.getVersion().major);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    static void getAllIndices() throws IOException {
        lowLevelCall((client) -> {
            try {
                Response response = client.performRequest("GET", "/_cat/indices?v", APPLICATION_JSON);
                new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
                        .lines()
                        .forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        getInfrastructureInfo();
        getAllIndices();
    }

}
