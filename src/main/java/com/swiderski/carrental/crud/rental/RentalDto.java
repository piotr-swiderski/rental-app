package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.client.ClientDto;

import java.time.LocalDate;
import java.util.Objects;

public class RentalDto extends AbstractDto {

    private CarDto car;
    private ClientDto client;
    private LocalDate rentalBegin;
    private LocalDate rentalEnd;

    public RentalDto(long id, CarDto car, ClientDto client, LocalDate rentalBegin, LocalDate rentalEnd) {
        this.car = car;
        this.client = client;
        this.rentalBegin = rentalBegin;
        this.rentalEnd = rentalEnd;
        this.id = id;
    }

    public RentalDto() {
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
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
        RentalDto rentalDto = (RentalDto) o;
        return id == rentalDto.id &&
                Objects.equals(car, rentalDto.car) &&
                Objects.equals(client, rentalDto.client) &&
                Objects.equals(rentalBegin, rentalDto.rentalBegin) &&
                Objects.equals(rentalEnd, rentalDto.rentalEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, client, rentalBegin, rentalEnd);
    }

    @Override
    public String toString() {
        return "RentalDto{" +
                "car=" + car +
                ", client=" + client +
                ", rentalBegin=" + rentalBegin +
                ", rentalEnd=" + rentalEnd +
                ", id=" + id +
                '}';
    }
}
