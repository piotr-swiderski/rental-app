package com.swiderski.carrental.controllers;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.dto.RentalDto;
import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Rental;
import com.swiderski.carrental.mapper.CarMapper;
import com.swiderski.carrental.mapper.RentalMapper;
import com.swiderski.carrental.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/car-rental-api/rent")
public class RentalController {

    private final RentService rentService;
    private final RentalMapper rentalMapper = RentalMapper.INSTANCE;
    private final CarMapper carMapper = CarMapper.INSTANCE;

    @Autowired
    public RentalController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/all")
    public Set<RentalDto> getAllRentals() {
        Set<Rental> allRentals = rentService.getAllRentals();
        return rentalMapper.rentalSetToRentalDtoSet(allRentals);
    }

    @GetMapping("/availableCars")
    public Set<CarDto> getAllAvailableCars() {
        Set<Car> availableCars = rentService.getAvailableCars();
        return carMapper.carSetToCarDtoSet(availableCars);
    }

    @GetMapping("/{id}")
    public RentalDto getRentalById(@PathVariable long id) {
        Rental rentalById = rentService.getRentalById(id);
        return rentalMapper.rentalToRentalDto(rentalById);
    }

    @GetMapping("/rentedCars")
    public Set<CarDto> getRentedCars() {
        Set<Car> rentedCars = rentService.getRentedCars();
        return carMapper.carSetToCarDtoSet(rentedCars);
    }

    @PostMapping("/rent")
    public RentalDto rentCar(@RequestParam long carId, @RequestParam long clientId) {
        Rental rental = rentService.rentCar(carId, clientId);
        return rentalMapper.rentalToRentalDto(rental);
    }

    @PostMapping("/return")
    public RentalDto returnCar(@RequestParam long rentalId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnedDate) {
        Rental rental = rentService.returnCar(rentalId, returnedDate);
        return rentalMapper.rentalToRentalDto(rental);
    }

}
