package com.swiderski.carrental.rental;

import com.swiderski.carrental.abstraction.AbstractController;
import com.swiderski.carrental.car.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/car-rental-api/rent")
public class RentalController extends AbstractController<RentService, RentalDto> {

    private final RentService rentService;

    @Autowired
    public RentalController(RentService service, RentService rentService) {
        super(service);
        this.rentService = rentService;
    }

    @GetMapping("/availableCars")
    public List<CarDto> getAllAvailableCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return rentService.getAvailableCars(pageNo, pageSize, sortBy);
    }

    @GetMapping("/rentedCars")
    public List<CarDto> getRentedCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return rentService.getRentedCars(pageNo, pageSize, sortBy);
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
