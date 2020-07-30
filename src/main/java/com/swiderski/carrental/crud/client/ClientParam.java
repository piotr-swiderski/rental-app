package com.swiderski.carrental.crud.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientParam {

    private String name;
    private String surname;
    private String email;
    private String city;
    private String street;
    private String zipCode;
    private String phone;

}
