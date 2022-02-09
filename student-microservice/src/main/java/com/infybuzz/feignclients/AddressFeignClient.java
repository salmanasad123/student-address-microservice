package com.infybuzz.feignclients;

import com.infybuzz.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// url of our service we want to get data from
@FeignClient(name = "address-service", path = "/api/address")
public interface AddressFeignClient {

    @RequestMapping("/getById/{id}")
    public AddressResponse getAddressById(@PathVariable(name = "id") long id);
}
