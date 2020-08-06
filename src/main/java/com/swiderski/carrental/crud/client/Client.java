package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "client_table")
public class Client extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "phone")
    private String phone;

    public Client() {
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
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(email, client.email) &&
                Objects.equals(city, client.city) &&
                Objects.equals(street, client.street) &&
                Objects.equals(zipCode, client.zipCode) &&
                Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, city, street, zipCode, phone);
    }

    public static final class ClientBuilder {
        private long id;
        private String name;
        private String surname;
        private String email;
        private String city;
        private String street;
        private String zipCode;
        private String phone;

        private ClientBuilder() {
        }

        public static ClientBuilder aClient() {
            return new ClientBuilder();
        }

        public ClientBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ClientBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ClientBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ClientBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public ClientBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public ClientBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ClientBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Client build() {
            Client client = new Client();
            client.setId(id);
            client.setName(name);
            client.setSurname(surname);
            client.setEmail(email);
            client.setCity(city);
            client.setStreet(street);
            client.setZipCode(zipCode);
            client.setPhone(phone);
            return client;
        }
    }
}


