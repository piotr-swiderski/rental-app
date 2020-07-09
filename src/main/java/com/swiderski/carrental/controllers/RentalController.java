package com.swiderski.carrental.controllers;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.dto.RentalDto;
import com.swiderski.carrental.services.RentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/car-rental-api/rent")
public class RentalController extends AbstractController<RentService, RentalDto> {

    private final RentService rentService;

    public RentalController(RentService service, RentService rentService) {
        super(service);
        this.rentService = rentService;
    }

    @GetMapping("/availableCars")
    public Set<CarDto> getAllAvailableCars() {
        return rentService.getAvailableCars();
    }

    @GetMapping("/rentedCars")
    public Set<CarDto> getRentedCars() {
        return rentService.getRentedCars();
    }

    @PostMapping("/rent")
    public RentalDto rentCar(@RequestParam long carId, @RequestParam long clientId) {
        return rentService.rentCar(carId, clientId);
    }

    @PostMapping("/return")
    public RentalDto returnCar(@RequestParam long rentalId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnedDate) {
        return rentService.returnCar(rentalId, returnedDate);
    }

}
