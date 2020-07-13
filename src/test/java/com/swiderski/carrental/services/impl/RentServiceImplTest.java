package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.car.Car;
import com.swiderski.carrental.car.CarDto;
import com.swiderski.carrental.car.CarMapper;
import com.swiderski.carrental.car.CarService;
import com.swiderski.carrental.client.ClientDto;
import com.swiderski.carrental.client.ClientMapper;
import com.swiderski.carrental.client.ClientService;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.rental.RentServiceImpl;
import com.swiderski.carrental.rental.Rental;
import com.swiderski.carrental.rental.RentalDto;
import com.swiderski.carrental.rental.RentalMapper;
import com.swiderski.carrental.rental.RentalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

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

    @Before
    public void init_mocks() {
        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class); // Initialization of the mapper
        ReflectionTestUtils.setField(rentalMapper, "clientMapper", clientMapper);
        CarMapper carMapper = Mappers.getMapper(CarMapper.class); // Initialization of the mapper
        ReflectionTestUtils.setField(rentalMapper, "carMapper", carMapper);
    }

    @Test
    public void getRentalById_shouldReturnedRentalById() {
        //given
        Rental rental = getRental();
        RentalDto rentalDto = getRentalDto();
        when(rentalRepository.findById(any())).thenReturn(Optional.of(rental));
        //when
        RentalDto rentalById = rentService.getById(rentalId);
        //then
        assertEquals(rentalDto, rentalById);
    }

    @Test
    public void getRentalById_shouldThrowNotFoundException() {
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());
        String expectedMessages = "Could not find " + "entity" + " with id = " + rentalId;

        Throwable exception = assertThrows(NotFoundException.class, () -> rentService.getById(rentalId));
        assertEquals(expectedMessages, exception.getMessage());
    }

    @Test
    public void rentCar_shouldRentCar() {
        //given
        CarDto carDto = getCarDto();
        Car car = getCar();
        ClientDto clientDto = getClientDto();
        Rental rental = getRental();
        RentalDto rentalExpectedDto = getRentalDto();
        when(clientService.getById(clientId)).thenReturn(clientDto);
        when(rentalRepository.getCarToRent(carId)).thenReturn(Optional.of(car));
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
        when(rentalRepository.findAllAvailableCar(any(PageRequest.class))).thenReturn(new PageImpl<>(cars));
        //when
        List<CarDto> availableCars = rentService.getAvailableCars(pageNo, pageSize, sortBy);
        //then
        assertEquals(2, availableCars.size());
    }

    @Test
    public void getRentedCars() {
        //given
        List<Car> cars = getCars();
        List<CarDto> carsDto = getCarsDto();
        when(rentalRepository.findAllRentedCars(any(PageRequest.class))).thenReturn(new PageImpl<>(cars));
        //when
        List<CarDto> rentedCars = rentService.getRentedCars(pageNo, pageSize, sortBy);
        //then
        assertEquals(2, rentedCars.size());
    }

    @Test
    public void getAllRentals() {
        //given
        List<Rental> rentals = getRentals();
        List<RentalDto> rentalsDto = getRentalsDto();
        when(rentalRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(rentals));
        //when
        List<RentalDto> all = rentService.getAll(pageNo, pageSize, sortBy);
        //then
        assertEquals(rentalsDto, all);
    }
}