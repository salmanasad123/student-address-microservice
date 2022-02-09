package com.infybuzz.controller;

import com.infybuzz.request.CreateAddressRequest;
import com.infybuzz.response.AddressResponse;
import com.infybuzz.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest addressRequest) {

        return addressService.createAddress(addressRequest);
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getAddressById(@PathVariable(name = "id") long addressId) {

        return addressService.getAddressById(addressId);
    }

}
