package com.swiderski.carrental.utils;

import com.swiderski.carrental.car.CarDto;
import com.swiderski.carrental.client.ClientDto;
import com.swiderski.carrental.rental.RentalDto;
import com.swiderski.carrental.car.Car;
import com.swiderski.carrental.client.Client;
import com.swiderski.carrental.car.EngineType;
import com.swiderski.carrental.rental.Rental;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static final long carId = 0;
    public static final String carBrand = "Toyota";
    public static final String carColour = "Red";
    public static final String carModelName = "corolla";
    public static final String carModelVersion = "LE";
    public static final EngineType carEngineType = EngineType.diesel;
    public static final int carProductionYear = 2020;
    public static final double carCost = 2000;

    public static final long carTwoId = 1;
    public static final String carTwoBrand = "Honda";
    public static final String carTwoColour = "blue";
    public static final String carTwoModelName = "civic";
    public static final String carTwoModelVersion = "2";
    public static final EngineType carTwoEngineType = EngineType.benzine;
    public static final int carTwoProductionYear = 2000;
    public static final double carTwoCost = 800;


    public static final long clientId = 0;
    public static final String clientName = "Jan";
    public static final String clientSurname = "Kowalski";
    public static final String clientEmail = "jankowalski@gmail.com";
    public static final String clientStreet = "Zwierzyniecka 12";
    public static final String clientCity = "Bialystok";
    public static final String clientZipCode = "12-122";
    public static final String clientPhone = "123456789";

    public static final long clientTwoId = 1;
    public static final String clientTwoName = "John";
    public static final String clientTwoSurname = "Malinowski";
    public static final String clientTwoEmail = "janmalinowski@gmail.com";
    public static final String clientTwoStreet = "Pogodna 12";
    public static final String clientTwoCity = "Bialystok";
    public static final String clientTwoZipCode = "12-122";
    public static final String clientTwoPhone = "123456789";

    public static final long rentalId = 0;
    public static final long rentalSecondId = 1;
    public static final LocalDate rentalBegin = LocalDate.of(2020, 5, 15);
    public static final LocalDate rentalEnd = LocalDate.of(2020, 5, 25);

    public static final int pageNo = 0;
    public static final int pageSize = 10;
    public static final String sortBy = "id";

    public static Client getClient() {
        return Client.ClientBuilder.aClient()
                .withId(clientId)
                .withName(clientName)
                .withSurname(clientSurname)
                .withEmail(clientEmail)
                .withCity(clientCity)
                .withStreet(clientStreet)
                .withZipCode(clientZipCode)
                .withPhone(clientPhone)
                .build();
    }

    public static Client getSecondClient() {
        return Client.ClientBuilder.aClient()
                .withId(carTwoId)
                .withName(clientTwoName)
                .withSurname(clientTwoSurname)
                .withEmail(clientTwoEmail)
                .withCity(clientTwoCity)
                .withStreet(clientTwoStreet)
                .withZipCode(clientTwoZipCode)
                .withPhone(clientTwoPhone)
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
                .withId(carId)
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
                .withId(carId)
                .withBrand(carBrand)
                .withColour(carColour)
                .withCost(carCost)
                .withModel(carModelName)
                .withModelVersion(carModelVersion)
                .withProductionYear(carProductionYear)
                .withEngineType(carEngineType)
                .build();
    }

    public static Car getSecondCar() {
        return Car.CarBuilder.aCar()
                .withId(carTwoId)
                .withBrand(carTwoBrand)
                .withColour(carTwoColour)
                .withCost(carTwoCost)
                .withModel(carTwoModelName)
                .withModelVersion(carTwoModelVersion)
                .withProductionYear(carTwoProductionYear)
                .withEngineType(carTwoEngineType)
                .build();
    }

    public static CarDto getSecondCarDto() {
        return CarDto.CarDtoBuilder.aCarDto()
                .withId(carTwoId)
                .withBrand(carTwoBrand)
                .withColour(carTwoColour)
                .withCost(carTwoCost)
                .withModel(carTwoModelName)
                .withModelVersion(carTwoModelVersion)
                .withProductionYear(carTwoProductionYear)
                .withEngineType(carTwoEngineType)
                .build();
    }

    public static Rental getRental() {
        return new Rental(rentalId, getCar(), getClient(), rentalBegin, rentalEnd);
    }

    public static Rental getSecondRental() {
        return new Rental(rentalSecondId, getSecondCar(), getSecondClient(), rentalBegin, rentalEnd);
    }

    public static RentalDto getRentalDto() {
        return new RentalDto(rentalId, getCarDto(), getClientDto(), rentalBegin, rentalEnd);
    }

    public static List<Car> getCars() {
        return Arrays.asList(getCar(), getSecondCar());
    }


    public static List<CarDto> getCarsDto() {
        return Arrays.asList(getCarDto(), getSecondCarDto());
    }

    public static List<Client> getClients() {
        return Arrays.asList(getClient(), getSecondClient());
    }

    public static List<ClientDto> getClientsDto() {
        return Arrays.asList(getClientDto(), getClientDto());
    }

    public static List<Rental> getRentals() {
        return Arrays.asList(getRental(), getSecondRental());
    }

}
