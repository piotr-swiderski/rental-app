package com.swiderski.carrental.rental;

import com.swiderski.carrental.abstraction.CommonService;
import com.swiderski.carrental.car.CarDto;

import java.time.LocalDate;
import java.util.List;

public interface RentService extends CommonService<RentalDto> {

    RentalDto rentCar(long carId, long clientId);

    RentalDto returnCar(long id, LocalDate returnDate);

    List<CarDto> getAvailableCars(int pageNo, int pageSize, String sortBy);

    List<CarDto> getRentedCars(int pageNo, int pageSize, String sortBy);
}
