package com.kafka.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final  OrderProducer orderProducer;

    @Autowired
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping(path = "/produce")
    public String saveAndProduceOrder(@RequestBody Order order){
        Order orderWithId = orderRepository.save(order);
        System.out.println(orderWithId);
        orderProducer.produceOrder(orderWithId);
        return "saved in orders DB and produced to kafka order_topic";
    }

    public void updateOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setShipped(true);
        orderRepository.save(order);
        System.out.println(order);
        System.out.println("Order is shipped");
    }
}
