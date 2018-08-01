package de.syed.elasticsearchschool;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ElasticInstanceInfo.getInfrastructureInfo();
        ElasticInstanceInfo.getAllIndices();
    }

}
