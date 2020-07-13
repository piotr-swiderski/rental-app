package com.swiderski.carrental.rental;

import com.swiderski.carrental.abstraction.CommonRepository;
import com.swiderski.carrental.car.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends CommonRepository<Rental> {

    @Query("SELECT c FROM car_table c " +
            "where c.id not in (" +
            "select r.car.id from rental_table r " +
            "where r.rentalEnd is null) ")
    Page<Car> findAllAvailableCar(Pageable pageable);

    @Query("SELECT c FROM car_table c " +
            "where c.id in (" +
            "select r.car.id from rental_table r " +
            "where r.rentalEnd is null and r.rentalBegin is not null ) ")
    Page<Car> findAllRentedCars(Pageable pageable);

    @Query("SELECT c FROM car_table c " +
            "where c.id = ?1 and c.id not in (" +
            "select r.car.id from rental_table r " +
            "where r.rentalEnd is null) ")
    Optional<Car> getCarToRent(long carId);
}
