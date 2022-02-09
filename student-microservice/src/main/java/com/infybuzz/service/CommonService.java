package com.infybuzz.service;

import com.infybuzz.feignclients.AddressFeignClient;
import com.infybuzz.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    Logger logger = LoggerFactory.getLogger(CommonService.class);

    long count = 1;

    @Autowired
    AddressFeignClient addressFeignClient;

    // name is the instance name we have provided in application.properties file which is addressService
    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId) {

        logger.info("Count = " + count);
        count++;
        AddressResponse addressResponse = addressFeignClient.getAddressById(addressId);

        return addressResponse;
    }


    // return dummy response in fallback method
    // we can also add an optional method parameter throwable which allow us to log exceptions
    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable) {

        logger.error("Error = " + throwable);
        AddressResponse addressResponse = new AddressResponse();

        return addressResponse;
    }
}
