package com.swiderski.carrental.entity;

import com.sun.xml.bind.v2.model.core.ID;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "rental_table")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private long id;

    @ManyToOne
    @JoinColumn(name="car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @Column(name =  "date_rented_begin")
    private LocalDate rentalBegin;

    @Column(name = "date_rented_end")
    private LocalDate rentalEnd;

    public Rental() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getRentalBegin() {
        return rentalBegin;
    }

    public void setRentalBegin(LocalDate rentalBegin) {
        this.rentalBegin = rentalBegin;
    }

    public LocalDate getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(LocalDate rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id == rental.id &&
                Objects.equals(car, rental.car) &&
                Objects.equals(client, rental.client) &&
                Objects.equals(rentalBegin, rental.rentalBegin) &&
                Objects.equals(rentalEnd, rental.rentalEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, client, rentalBegin, rentalEnd);
    }
}
