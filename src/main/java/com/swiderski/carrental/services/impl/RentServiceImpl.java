package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.dto.ClientDto;
import com.swiderski.carrental.dto.RentalDto;
import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.entity.Rental;
import com.swiderski.carrental.exception.CarRentedException;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.mapper.CarMapper;
import com.swiderski.carrental.mapper.ClientMapper;
import com.swiderski.carrental.mapper.RentalMapper;
import com.swiderski.carrental.repository.RentalRepository;
import com.swiderski.carrental.services.ClientService;
import com.swiderski.carrental.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class RentServiceImpl implements RentService {

    private final ClientService clientService;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final ClientMapper clientMapper;
    private final CarMapper carMapper;

    @Autowired
    public RentServiceImpl(ClientService clientService, RentalRepository rentalRepository, RentalMapper rentalMapper, ClientMapper clientMapper, CarMapper carMapper) {
        this.clientService = clientService;
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.clientMapper = clientMapper;
        this.carMapper = carMapper;
    }

    @Override
    public RentalDto save(RentalDto rentalDto) {
        Rental rental = rentalMapper.rentalDtoToRental(rentalDto);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.rentalToRentalDto(savedRental);
    }

    @Override
    public RentalDto getById(long id) {
        Rental rental = getRental(id);
        return rentalMapper.rentalToRentalDto(rental);
    }


    @Override
    public List<RentalDto> getAll(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Rental> pagedResult = rentalRepository.findAll(paging).getContent();
        return rentalMapper.rentalListToRentalDtoList(pagedResult);
    }

    @Override
    public RentalDto update(long id, RentalDto rentalDto) {
        rentalDto.setId(id);
        rentalMapper.rentalDtoToRental(rentalDto);
        return rentalDto;
    }

    @Override
    public RentalDto delete(long id) {
        Rental rental = getRental(id);
        rentalRepository.delete(rental);
        return rentalMapper.rentalToRentalDto(rental);
    }

    @Override
    public RentalDto rentCar(long carId, long clientId) {
        ClientDto clientDto = clientService.getById(clientId);
        CarDto carDto = getCarToRent(carId);

        Car car = carMapper.carDtoToCar(carDto);
        Client client = clientMapper.clientDtoDtoClient(clientDto);

        Rental rentalTosSave = Rental.RentalBuilder.aRental()
                .withClient(client)
                .withCar(car)
                .build();
        Rental savedRental = rentalRepository.save(rentalTosSave);

        return rentalMapper.rentalToRentalDto(savedRental);
    }

    private CarDto getCarToRent(long carId) {
        Car car = rentalRepository.getCarToRent(carId)
                .orElseThrow(() -> new CarRentedException(carId));
        return carMapper.carToCarDto(car);
    }

    @Override
    public RentalDto returnCar(long id, LocalDate returnDate) {
        Rental rental = getRental(id);
        rental.setRentalEnd(returnDate);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.rentalToRentalDto(savedRental);
    }

    @Override
    public Set<CarDto> getAvailableCars() {
        Set<Car> allAvailableCar = rentalRepository.findAllAvailableCar();
        return carMapper.carSetToCarDtoSet(allAvailableCar);
    }

    @Override
    public Set<CarDto> getRentedCars() {
        Set<Car> allRentedCars = rentalRepository.findAllRentedCars();
        return carMapper.carSetToCarDtoSet(allRentedCars);
    }

    private Rental getRental(long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, Rental.class.getSimpleName()));
    }

}
