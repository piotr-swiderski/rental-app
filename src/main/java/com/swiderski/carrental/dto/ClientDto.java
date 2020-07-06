package com.swiderski.carrental.dto;

import javax.persistence.Column;
import java.util.Objects;

public class ClientDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String street;
    private String zipCode;
    private String phone;

    public ClientDto() {
    }

    public ClientDto(long id, String name, String surname, String email, String city, String street, String zipCode, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public static final class ClientDtoBuilder {
        private long id;
        private String name;
        private String surname;
        private String email;
        private String city;
        private String street;
        private String zipCode;
        private String phone;

        private ClientDtoBuilder() {
        }

        public static ClientDtoBuilder aClientDto() {
            return new ClientDtoBuilder();
        }

        public ClientDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ClientDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ClientDtoBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ClientDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientDtoBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public ClientDtoBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public ClientDtoBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ClientDtoBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ClientDto build() {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(id);
            clientDto.setName(name);
            clientDto.setSurname(surname);
            clientDto.setEmail(email);
            clientDto.setCity(city);
            clientDto.setStreet(street);
            clientDto.setZipCode(zipCode);
            clientDto.setPhone(phone);
            return clientDto;
        }
    }
}
