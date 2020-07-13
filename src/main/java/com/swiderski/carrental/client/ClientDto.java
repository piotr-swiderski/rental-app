package com.swiderski.carrental.client;

import com.swiderski.carrental.abstraction.AbstractDto;

import java.util.Objects;

public class ClientDto extends AbstractDto {

    private String name;
    private String surname;
    private String email;
    private String city;
    private String street;
    private String zipCode;
    private String phone;

    public ClientDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return id == clientDto.id &&
                Objects.equals(name, clientDto.name) &&
                Objects.equals(surname, clientDto.surname) &&
                Objects.equals(email, clientDto.email) &&
                Objects.equals(city, clientDto.city) &&
                Objects.equals(street, clientDto.street) &&
                Objects.equals(zipCode, clientDto.zipCode) &&
                Objects.equals(phone, clientDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, city, street, zipCode, phone);
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

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                '}';
    }
}
