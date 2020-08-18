package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.CommonParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientParam implements CommonParam {

    private String name;
    private String surname;
    @Email
    private String email;
    private String city;
    private String street;
    private String zipCode;
    private String phone;

}
