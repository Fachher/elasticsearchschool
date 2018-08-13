package de.syed.elasticsearchschool;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        ShipmentTestDataCreator.createTestData();
        Thread.sleep(900);
        ShipmentRepository shipmentRepository = new ShipmentRepository();
        shipmentRepository.findByTrackingNumber("tn1*");

    }

}
