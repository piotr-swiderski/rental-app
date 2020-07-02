package com.swiderski.carrental.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "client_table")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "money")
    private double money;

    @OneToMany
    private Set<Car> rentCars;

    public Client() {
    }

    public Client(long id, String name, String surname, String email, double money, Set<Car> rentCars) {
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

    public Set<Car> getRentCars() {
        return rentCars;
    }

    public void setRentCars(Set<Car> rentCars) {
        this.rentCars = rentCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Double.compare(client.money, money) == 0 &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(email, client.email) &&
                Objects.equals(rentCars, client.rentCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, money, rentCars);
    }

}

