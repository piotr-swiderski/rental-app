package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.car.CarDto;
import com.swiderski.carrental.client.ClientDto;
import com.swiderski.carrental.rental.RentalDto;
import com.swiderski.carrental.car.Car;
import com.swiderski.carrental.rental.Rental;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.car.CarMapper;
import com.swiderski.carrental.client.ClientMapper;
import com.swiderski.carrental.rental.RentalMapper;
import com.swiderski.carrental.rental.RentalRepository;
import com.swiderski.carrental.car.CarService;
import com.swiderski.carrental.client.ClientService;
import com.swiderski.carrental.rental.RentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
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

    @Spy
    RentalMapper rentalMapper = RentalMapper.INSTANCE;

    @Spy
    CarMapper carMapper = CarMapper.INSTANCE;

    @Spy
    ClientMapper clientMapper = ClientMapper.INSTANCE;


    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRentalById_shouldReturnedRentalById() {
        //given
        Rental rental = getRental();
        RentalDto rentalDto = getRentalDto();
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        //when
        RentalDto rentalById = rentService.getById(rentalId);
        //then
        assertEquals(rentalDto, rentalById);
    }

    @Test
    public void getRentalById_shouldThrowNotFoundException() {
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());
        String expectedMessages = "Could not find " + Rental.class.getSimpleName() + " with id = " + rentalId;

        Throwable exception = assertThrows(NotFoundException.class, () -> rentService.getById(rentalId));
        assertEquals(expectedMessages, exception.getMessage());
    }

    @Test
    public void rentCar_shouldRentCar() {
        //given
        CarDto car = getCarDto();
        ClientDto clientDto = getClientDto();
        Rental rental = getRental();
        RentalDto rentalExpectedDto = getRentalDto();
        when(carService.getById(carId)).thenReturn(car);
        when(clientService.getById(clientId)).thenReturn(clientDto);
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        //when
        RentalDto rentalSaved = rentService.rentCar(carId, clientId);
        //then
        assertEquals(rentalExpectedDto, rentalSaved);
    }

    @Test
    public void returnCar_shouldReturnedCar() {
        //given
        Rental rental = getRental();
        Rental rentalExpected = getRental();
        rentalExpected.setRentalEnd(rentalEnd);
        RentalDto rentalDtoExpected = getRentalDto();
        rentalDtoExpected.setRentalEnd(rentalEnd);
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(any(Rental.class))).thenReturn(rentalExpected);
        //when
        RentalDto finishRental = rentService.returnCar(rentalId, rentalEnd);
        //then
        assertEquals(rentalDtoExpected, finishRental);
    }

    @Test
    public void getAvailableCars() {
        //given
        List<Car> cars = getCars();
        List<CarDto> carsDto = getCarsDto();
        when(rentalRepository.findAllAvailableCar()).thenReturn((Set<Car>) cars);
        //when
        Set<CarDto> availableCars = rentService.getAvailableCars();
        //then
        assertEquals(2, availableCars.size());
    }

    @Test
    public void getRentedCars() {
        //given
        List<Car> cars = getCars();
        List<CarDto> carsDto = getCarsDto();
        when(rentalRepository.findAllRentedCars()).thenReturn(new HashSet<>(cars));
        //when
        Set<CarDto> rentedCars = rentService.getRentedCars();
        //then
        assertEquals(2, rentedCars.size());
    }

    @Test
    public void getAllRentals() {
        //given
        List<Rental> rentals = getRentals();
        when(rentalRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(rentals));
        //when
        Collection<RentalDto> all = rentService.getAll(pageNo, pageSize, sortBy);
        //then
        assertEquals(rentals, all);
    }
}