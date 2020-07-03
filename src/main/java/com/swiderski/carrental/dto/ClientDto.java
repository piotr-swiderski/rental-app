package com.swiderski.carrental.dto;

import java.util.Objects;

public class ClientDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;

    public ClientDto() {
    }

    public ClientDto(long id, String name, String surname, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                Objects.equals(address, clientDto.address) &&
                Objects.equals(phone, clientDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, address, phone);
    }
}
