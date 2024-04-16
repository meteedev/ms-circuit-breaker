package com.meteeshop.order.service;

import com.meteeshop.order.dto.AddressDto;
import com.meteeshop.order.model.Failure;
import com.meteeshop.order.model.Order;
import com.meteeshop.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderService {

    private final OrderRepository orderRepository;


    private RestTemplate restTemplate;
    private static final String SERVICE_NAME = "order-service";
    private static final String ADDRESS_SERVICE_URL = "http://localhost:9090/addresses/";


    public OrderService(OrderRepository orderRepository,RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackOrder")
    public ResponseEntity<Object> getOrderByPostCode(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Order Not Found: " + orderNumber));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressDto> entity = new HttpEntity<>(null, headers);
        ResponseEntity<AddressDto> response = restTemplate.exchange(
                (ADDRESS_SERVICE_URL + order.getPostalCode()), HttpMethod.GET, entity,
                AddressDto.class);
        AddressDto addressDTO = response.getBody();
        if (addressDTO != null) {
            order.setShippingState(addressDTO.getState());
            order.setShippingCity(addressDTO.getCity());
        }
        return ResponseEntity.ok(order);
    }


    private ResponseEntity<Object> fallbackOrder(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Failure("Address service is not responding properly"));
    }

}
