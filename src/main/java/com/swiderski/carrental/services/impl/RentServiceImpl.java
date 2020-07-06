package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.entity.Rental;
import com.swiderski.carrental.exception.CarRentedException;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.repository.RentalRepository;
import com.swiderski.carrental.services.CarService;
import com.swiderski.carrental.services.ClientService;
import com.swiderski.carrental.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class RentServiceImpl implements RentService {

    private final CarService carService;
    private final ClientService clientService;
    private final RentalRepository rentalRepository;

    @Autowired
    public RentServiceImpl(CarService carService, ClientService clientService, RentalRepository rentalRepository) {
        this.carService = carService;
        this.clientService = clientService;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental getRentalById(long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, Rental.class.getSimpleName()));
    }

    @Override
    public Rental rentCar(long carId, long clientId) {
        Client clientById = clientService.getClientById(clientId);
        Car carById = getAvailableCars().stream()
                .filter(c -> c.getId() == carId)
                .findFirst()
                .orElseThrow(() -> new CarRentedException(carId));

        Rental rental = Rental.RentalBuilder.aRental()
                .withCar(carById)
                .withClient(clientById)
                .build();
        return rentalRepository.save(rental);

    }

    @Override
    public Rental returnCar(long id, LocalDate returnDate) {
        Rental rentalById = getRentalById(id);
        rentalById.setRentalEnd(returnDate);
        return rentalRepository.save(rentalById);
    }

    @Override
    public Set<Car> getAvailableCars() {
        return rentalRepository.findAllAvailableCar();
    }

    @Override
    public Set<Car> getRentedCars() {
        return rentalRepository.findAllRentedCars();
    }

    @Override
    public Set<Rental> getAllRentals() {
        return new HashSet<>(rentalRepository.findAll());
    }
}
