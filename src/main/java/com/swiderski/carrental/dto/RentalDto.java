package com.swiderski.carrental.dto;

import java.time.LocalDate;
import java.util.Objects;

public class RentalDto {

    private long id;
    private CarDto carDto;
    private ClientDto clientDto;
    private LocalDate rentalBegin;
    private LocalDate rentalEnd;

    public RentalDto(long id, CarDto carDto, ClientDto clientDto, LocalDate rentalBegin, LocalDate rentalEnd) {
        this.id = id;
        this.carDto = carDto;
        this.clientDto = clientDto;
        this.rentalBegin = rentalBegin;
        this.rentalEnd = rentalEnd;
    }

    public RentalDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public ClientDto getClientDto() {
        return clientDto;
    }

    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
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
                Objects.equals(carDto, rentalDto.carDto) &&
                Objects.equals(clientDto, rentalDto.clientDto) &&
                Objects.equals(rentalBegin, rentalDto.rentalBegin) &&
                Objects.equals(rentalEnd, rentalDto.rentalEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carDto, clientDto, rentalBegin, rentalEnd);
    }
}
