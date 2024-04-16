package com.meteeshop.order.config;

import com.meteeshop.order.model.Order;
import com.meteeshop.order.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataConfig {
    private final OrderRepository orderRepository;

    public DataConfig(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setupData() {
        orderRepository.saveAll(Arrays.asList(
                Order.builder().id(1).orderNumber("0c70c0c2").postalCode("1000001").build(),
                Order.builder().id(2).orderNumber("7f8f9f15").postalCode("1100000").build(),
                Order.builder().id(3).orderNumber("394627b2").postalCode("2100001").build()));
    }
}
