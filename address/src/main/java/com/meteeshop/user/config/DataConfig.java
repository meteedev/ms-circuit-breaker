package com.meteeshop.user.config;

import com.meteeshop.user.model.Address;
import com.meteeshop.user.repository.AddressRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataConfig {

    private final AddressRepository addressRepository;

    public DataConfig(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @PostConstruct
    public void setupData() {
        addressRepository.saveAll(Arrays.asList(
                Address.builder().id(1).postalCode("1000001").state("Tokyo").city("Chiyoda")
                        .build(),
                Address.builder().id(2).postalCode("1100000").state("Tokyo").city("Taito").build(),
                Address.builder().id(3).postalCode("2100001").state("Kanagawa").city("Kawasaki")
                        .build()));
    }
}
