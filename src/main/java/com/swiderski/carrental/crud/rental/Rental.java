package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.AbstractEntity;
import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.client.Client;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "rental_table")
public class Rental extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, updatable = false, insertable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, updatable = false, insertable = false)
    private Client client;

    @Column(name = "date_rented_begin")
    @CreationTimestamp
    private LocalDate rentalBegin;

    @Column(name = "date_rented_end")
    private LocalDate rentalEnd;

    public Rental() {
    }

    public Rental(long id, Car car, Client client, LocalDate rentalBegin, LocalDate rentalEnd) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.rentalBegin = rentalBegin;
        this.rentalEnd = rentalEnd;
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


    public static final class RentalBuilder {
        private long id;
        private Car car;
        private Client client;
        private LocalDate rentalBegin;
        private LocalDate rentalEnd;

        private RentalBuilder() {
        }

        public static RentalBuilder aRental() {
            return new RentalBuilder();
        }

        public RentalBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public RentalBuilder withCar(Car car) {
            this.car = car;
            return this;
        }

        public RentalBuilder withClient(Client client) {
            this.client = client;
            return this;
        }

        public RentalBuilder withRentalBegin(LocalDate rentalBegin) {
            this.rentalBegin = rentalBegin;
            return this;
        }

        public RentalBuilder withRentalEnd(LocalDate rentalEnd) {
            this.rentalEnd = rentalEnd;
            return this;
        }

        public Rental build() {
            Rental rental = new Rental();
            rental.setId(id);
            rental.setCar(car);
            rental.setClient(client);
            rental.setRentalBegin(rentalBegin);
            rental.setRentalEnd(rentalEnd);
            return rental;
        }
    }
}
