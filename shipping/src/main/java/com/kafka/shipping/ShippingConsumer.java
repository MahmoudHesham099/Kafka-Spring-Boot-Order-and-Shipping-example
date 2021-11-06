package com.kafka.shipping;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShippingConsumer {

    @Autowired
    private ShippingController shippingController;

    @KafkaListener(topics = AppConstants.ORDER_TOPIC)
    public void consumeOrder(String shippingOrderString) throws IOException {
        System.out.println(String.format("##########\nConsumed Order-> %s\n##########", shippingOrderString));
        ObjectMapper mapper = new ObjectMapper();
        Shipping shippingOrder = mapper.readValue(shippingOrderString,Shipping.class);
        shippingController.saveShipping(shippingOrder);
    }
}
