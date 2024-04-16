package com.meteeshop.user.controller;

import com.meteeshop.user.model.Address;
import com.meteeshop.user.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{postalCode}")
    public Address getAddressByPostalCode(@PathVariable("postalCode") String postalCode) {
        return addressService.getAddressByPostalCode(postalCode);
    }



}
