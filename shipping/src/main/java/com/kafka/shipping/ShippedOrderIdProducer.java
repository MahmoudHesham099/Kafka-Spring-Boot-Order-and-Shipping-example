package com.kafka.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShippedOrderIdProducer {

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    public void produceOrderId(Long orderId){
        System.out.println(
                String.format("##########\nProduced Order Id-> %s\n##########", orderId));
        this.kafkaTemplate.send(AppConstants.SHIPPED_ORDER_TOPIC, orderId);
    }

}
