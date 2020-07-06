package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.entity.Rental;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.repository.RentalRepository;
import com.swiderski.carrental.services.CarService;
import com.swiderski.carrental.services.ClientService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.swiderski.carrental.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceImplTest {

    @Mock
    RentalRepository rentalRepository;

    @Mock
    ClientService clientService;

    @Mock
    CarService carService;

    @InjectMocks
    RentServiceImpl rentService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRentalById_shouldReturnedRentalById() {
        //given
        Rental rental = getRental();
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        //when
        Rental rentalById = rentService.getRentalById(rentalId);
        //then
        assertEquals(rental, rentalById);
    }

    @Test
    public void getRentalById_shouldThrowNotFoundException() {
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());
        String expectedMessages = "Could not find " + Rental.class.getSimpleName() + " with id = " + rentalId;

        Throwable exception = assertThrows(NotFoundException.class, () -> rentService.getRentalById(rentalId));
        assertEquals(expectedMessages, exception.getMessage());
    }

    @Test
    public void rentCar_shouldRentCar() {
        //given
        Car car = getCar();
        Client client = getClient();
        Rental rentalExpected = getRental();
        when(carService.getCarById(carId)).thenReturn(car);
        when(clientService.getClientById(clientId)).thenReturn(client);
        when(rentalRepository.save(any(Rental.class))).thenReturn(rentalExpected);
        //when
        Rental rental = rentService.rentCar(carId, clientId);
        //then
        assertEquals(rentalExpected, rental);
    }

    @Test
    public void returnCar_shouldReturnedCar() {
        //given
        Rental rental = getRental();
        Rental rentalExpected = getRental();
        rentalExpected.setRentalEnd(rentalEnd);
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(any(Rental.class))).thenReturn(rentalExpected);
        //when
        Rental finishRental = rentService.returnCar(rentalId, rentalEnd);
        //then
        assertEquals(rentalExpected, finishRental);
    }

    @Test
    public void getAvailableCars() {
        //given
        List<Car> cars = getCars();
        when(rentalRepository.findAllAvailableCar()).thenReturn(new HashSet<>(cars));
        //when
        Set<Car> availableCars = rentService.getAvailableCars();
        //then
        assertEquals(2, availableCars.size());
    }

    @Test
    public void getRentedCars() {
        //given
        List<Car> cars = getCars();
        when(rentalRepository.findAllRentedCars()).thenReturn(new HashSet<>(cars));
        //when
        Set<Car> rentedCars = rentService.getRentedCars();
        //then
        assertEquals(2, rentedCars.size());
    }

    @Test
    public void getAllRentals() {
        //given
        List<Rental> rentals = getRentals();
        when(rentalRepository.findAll()).thenReturn(rentals);
        //when
        Set<Rental> allRentals = rentService.getAllRentals();
        //then
        assertEquals(new HashSet<>(rentals), allRentals);
    }
}