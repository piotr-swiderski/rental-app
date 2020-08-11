package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.client.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalDto extends AbstractDto {

    private CarDto car;
    private ClientDto client;
    private LocalDate rentalBegin;
    private LocalDate rentalEnd;

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

    public RentalDto(Long id, CarDto car, ClientDto client, LocalDate rentalBegin, LocalDate rentalEnd) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.rentalBegin = rentalBegin;
        this.rentalEnd = rentalEnd;
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
