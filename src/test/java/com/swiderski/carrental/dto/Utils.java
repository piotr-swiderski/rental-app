package com.swiderski.carrental.dto;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.entity.EngineType;
import com.swiderski.carrental.entity.Rental;

import java.time.LocalDate;

public class Utils {

    public static final String carBrand = "Toyota";
    public static final String carColour = "Red";
    public static final EngineType carEngineType = EngineType.diesel;
    public static final int carProductionYear = 2020;
    public static final double carCost = 2000;

    public static final String clientName = "Jan";
    public static final String clientSurname = "Kowalski";
    public static final String clientEmail = "Email";
    public static final String clientAddress = "Bialystok 12";
    public static final String clientPhone = "123456789";

    public static final LocalDate rentalBegin = LocalDate.of(2020, 5, 15);
    public static final LocalDate rentalEnd = LocalDate.of(2020, 5, 25);

    public static Client getClient() {
        return new Client(1, clientName, clientSurname, clientEmail, clientAddress, clientPhone);
    }

    public static ClientDto getClientDto() {
        return new ClientDto(1, clientName, clientSurname, clientEmail, clientAddress, clientPhone);
    }

    public static Car getCar() {
        return new Car(1, carBrand, carColour, carEngineType, carProductionYear, carCost);
    }

    public static CarDto getCarDto() {
        return new CarDto(1, carBrand, carColour, carEngineType, carProductionYear, carCost);
    }

    public static Rental getRental() {
        return new Rental(1, getCar(), getClient(), rentalBegin, rentalEnd);
    }

    public static RentalDto getRentalDto() {
        return new RentalDto(1, getCarDto(), getClientDto(), rentalBegin, rentalEnd);
    }

}
