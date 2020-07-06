package com.swiderski.carrental.utils;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.dto.ClientDto;
import com.swiderski.carrental.dto.RentalDto;
import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.entity.EngineType;
import com.swiderski.carrental.entity.Rental;

import java.time.LocalDate;

public class Utils {

    public static final String carBrand = "Toyota";
    public static final String carColour = "Red";
    public static final String carModelName = "corolla";
    public static final String carModelVersion = "LE";
    public static final EngineType carEngineType = EngineType.diesel;
    public static final int carProductionYear = 2020;
    public static final double carCost = 2000;

    public static final String clientName = "Jan";
    public static final String clientSurname = "Kowalski";
    public static final String clientEmail = "Email";
    public static final String clientStreet = "Zwierzyniecka 12";
    public static final String clientCity = "Bialystok";
    public static final String clientZipCode = "12-122";
    public static final String clientPhone = "123456789";

    public static final LocalDate rentalBegin = LocalDate.of(2020, 5, 15);
    public static final LocalDate rentalEnd = LocalDate.of(2020, 5, 25);

    public static Client getClient() {
        return Client.ClientBuilder.aClient()
                .withName(clientName)
                .withSurname(clientSurname)
                .withEmail(clientEmail)
                .withCity(clientCity)
                .withStreet(clientStreet)
                .withZipCode(clientZipCode)
                .withPhone(clientPhone)
                .build();
    }

    public static ClientDto getClientDto() {
        return ClientDto.ClientDtoBuilder.aClientDto()
                .withName(clientName)
                .withSurname(clientSurname)
                .withEmail(clientEmail)
                .withCity(clientCity)
                .withStreet(clientStreet)
                .withZipCode(clientZipCode)
                .withPhone(clientPhone)
                .build();
    }

    public static Car getCar() {
        return Car.CarBuilder.aCar()
                .withBrand(carBrand)
                .withColour(carColour)
                .withCost(carCost)
                .withModel(carModelName)
                .withModelVersion(carModelVersion)
                .withProductionYear(carProductionYear)
                .withEngineType(carEngineType)
                .build();
    }

    public static CarDto getCarDto() {
        return CarDto.CarDtoBuilder.aCarDto()
                .withBrand(carBrand)
                .withColour(carColour)
                .withCost(carCost)
                .withModel(carModelName)
                .withModelVersion(carModelVersion)
                .withProductionYear(carProductionYear)
                .withEngineType(carEngineType)
                .build();
    }

    public static Rental getRental() {
        return new Rental(1, getCar(), getClient(), rentalBegin, rentalEnd);
    }

    public static RentalDto getRentalDto() {
        return new RentalDto(1, getCarDto(), getClientDto(), rentalBegin, rentalEnd);
    }

}
