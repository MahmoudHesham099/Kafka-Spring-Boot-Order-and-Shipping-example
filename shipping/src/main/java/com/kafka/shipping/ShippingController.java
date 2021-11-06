package com.kafka.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/shipping")
public class ShippingController {

    private final ShippedOrderIdProducer shippedOrderIdProducer;

    public ShippingController(ShippedOrderIdProducer shippedOrderIdProducer) {
        this.shippedOrderIdProducer = shippedOrderIdProducer;
    }

    @Autowired
    private ShippingRepository shippingRepository;


    @DeleteMapping(path = "/shipped/{orderId}")
    public String produceAndDeleteOrderId(@PathVariable Long orderId){
        shippedOrderIdProducer.produceOrderId(orderId);
        shippingRepository.deleteById(orderId);
        return "Order is shipped (deleted) from shippings database and Produced to kafka shipped_order_topic";
    }

    @GetMapping
    public List<Shipping> getOrdersToShipping(){
        return shippingRepository.findAll();
    }

    public void saveShipping(Shipping shippingOrder) {
        shippingRepository.save(shippingOrder);
        System.out.println("saved in Shipping DB");
    }
}
