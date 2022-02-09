package com.infybuzz.response;

import com.infybuzz.entity.Address;

public class AddressResponse {

    private long addressId;
    private String street;
    private String city;

    // convert address object into addressResponse, we can also use modelmapper library
    public AddressResponse(Address address) {
        this.addressId = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
