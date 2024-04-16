package com.meteeshop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Failure implements Type {
    private final String msg;
}