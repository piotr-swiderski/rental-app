package com.swiderski.carrental.repository;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT c FROM car_table c " +
            "where c.id not in (" +
            "select r.car.id from rental_table r " +
            "where r.rentalEnd is null) ")
    Set<Car> findAllAvailableCar();

    @Query("SELECT c FROM car_table c " +
            "where c.id in (" +
            "select r.car.id from rental_table r " +
            "where r.rentalEnd is null and r.rentalBegin is not null ) ")
    Set<Car> findAllRentedCars();

    @Query("SELECT c FROM car_table c " +
            "where c.id = ?1 and c.id not in (" +
            "select r.car.id from rental_table r " +
            "where r.rentalEnd is null) ")
    Optional<Car> getCarToRent(long carId);
}
