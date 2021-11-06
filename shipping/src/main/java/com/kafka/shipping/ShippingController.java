package com.kafka.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/shipping")
public class ShippingController {

    @Autowired
    private ShippingRepository shippingRepository;


    public void saveShipping(Shipping shippingOrder) {
        shippingRepository.save(shippingOrder);
        System.out.println("saved in Shipping DB");
    }
}
