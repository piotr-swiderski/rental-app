package com.swiderski.carrental.dto;

import java.util.Objects;
import java.util.Set;

public class ClientDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private double money;
    private Set<CarDto> rentCars;

    public ClientDto() {
    }

    public ClientDto(long id, String name, String surname, String email, double money, Set<CarDto> rentCars) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.money = money;
        this.rentCars = rentCars;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Set<CarDto> getRentCars() {
        return rentCars;
    }

    public void setRentCars(Set<CarDto> rentCars) {
        this.rentCars = rentCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return id == clientDto.id &&
                Double.compare(clientDto.money, money) == 0 &&
                Objects.equals(name, clientDto.name) &&
                Objects.equals(surname, clientDto.surname) &&
                Objects.equals(email, clientDto.email) &&
                Objects.equals(rentCars, clientDto.rentCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, money, rentCars);
    }

}
