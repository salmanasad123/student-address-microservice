package com.infybuzz.service;

import com.infybuzz.entity.Address;
import com.infybuzz.repository.AddressRepository;
import com.infybuzz.request.CreateAddressRequest;
import com.infybuzz.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressRepository addressRepository;

    public AddressResponse createAddress(CreateAddressRequest addressRequest) {

        logger.info("Inside createAddress method");

        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());

        Address newAddress = addressRepository.save(address);

        AddressResponse addressResponse = new AddressResponse(newAddress);

        return addressResponse;

    }

    public AddressResponse getAddressById(long addressId) {

        logger.info("Inside getAddressById " + addressId);

        Optional<Address> addressOptional = addressRepository.findById(addressId);

        if (addressOptional.isPresent()) {
            AddressResponse addressResponse = new AddressResponse(addressOptional.get());
            return addressResponse;
        }
        return null;
    }
}
