package com.meteeshop.order.controller;

import com.meteeshop.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Object> getByOrderNumber(@RequestParam("orderNumber") String orderNumber) {
        return ResponseEntity.ok(orderService.getOrderByPostCode(orderNumber)) ;
    }
}
