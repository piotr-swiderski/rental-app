package com.swiderski.carrental.rental;

import com.swiderski.carrental.abstraction.CommonService;
import com.swiderski.carrental.car.CarDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface RentService extends CommonService<RentalDto> {

    RentalDto rentCar(long carId, long clientId);

    RentalDto returnCar(long id, LocalDate returnDate);

    Page<CarDto> getAvailableCars(int pageNo, int pageSize, String sortBy);

    Page<CarDto> getRentedCars(int pageNo, int pageSize, String sortBy);
}
