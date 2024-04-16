package com.meteeshop.user.service;

import com.meteeshop.user.model.Address;
import com.meteeshop.user.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressByPostalCode(String postalCode) {
        return addressRepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new RuntimeException("Address Not Found: " + postalCode));
    }


}
