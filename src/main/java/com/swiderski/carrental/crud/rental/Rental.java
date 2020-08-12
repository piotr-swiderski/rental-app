package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.AbstractEntity;
import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "rental_table")
public class Rental extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "date_rented_begin")
    @CreationTimestamp
    private LocalDate rentalBegin;

    @Column(name = "date_rented_end")
    private LocalDate rentalEnd;

    public Rental(Long id, Car car, Client client, LocalDate rentalBegin, LocalDate rentalEnd) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.rentalBegin = rentalBegin;
        this.rentalEnd = rentalEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(car, rental.car) &&
                Objects.equals(client, rental.client) &&
                Objects.equals(rentalBegin, rental.rentalBegin) &&
                Objects.equals(rentalEnd, rental.rentalEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, client, rentalBegin, rentalEnd);
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
