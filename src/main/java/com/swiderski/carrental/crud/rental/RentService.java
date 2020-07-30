package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.CommonService;
import com.swiderski.carrental.crud.car.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RentService extends CommonService<RentalDto> {

    Page<RentalDto> getAll(RentalParam rentalParam, Pageable pageable);

    RentalDto rentCar(long carId, long clientId);

    RentalDto returnCar(long id, LocalDate returnDate);

    Page<CarDto> getAvailableCars(int pageNo, int pageSize, String sortBy);

    Page<CarDto> getRentedCars(int pageNo, int pageSize, String sortBy);

}
