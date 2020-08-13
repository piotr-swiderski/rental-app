package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarMapper;
import com.swiderski.carrental.crud.car.CarService;
import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientMapper;
import com.swiderski.carrental.crud.client.ClientService;
import com.swiderski.carrental.crud.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static com.swiderski.carrental.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        when(carService.getById(anyLong())).thenReturn(carDto);
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
        Page<CarDto> availableCars = rentService.getAvailableCars(pageRequest);
        //then
        assertEquals(2, availableCars.getTotalElements());
    }

    @Test
    public void getRentedCars() {
        //given
        List<Car> cars = getCars();
        List<CarDto> carsDto = getCarsDto();
        when(rentalRepository.findAllRentedCars(any(PageRequest.class))).thenReturn(new PageImpl<>(cars));
        //when
        Page<CarDto> rentedCars = rentService.getRentedCars(pageRequest);
        //then
        assertEquals(2, rentedCars.getTotalElements());
    }

    @Test
    public void getAllRentals() {
        //given
        List<Rental> rentals = getRentals();
        List<RentalDto> rentalsDto = getRentalsDto();
        when(rentalRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(rentals));
        //when
        Page<RentalDto> all = rentService.getAll(new RentalParam(), pageRequest);
        //then
        assertEquals(rentalsDto, all.getContent());
    }
}