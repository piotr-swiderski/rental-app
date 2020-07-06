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
            "left join rental_table r on c.id = r.car.id " +
            "where r.rentalBegin is null or r.rentalEnd is not null ")
    Set<Car> findAllAvailableCar();

    @Query("select c from  car_table c " +
            "left join rental_table r on c.id = r.car.id " +
            "where r.rentalBegin is not null and r.rentalEnd is null")
    Set<Car> findAllRentedCars();

    @Query("select c from car_table c " +
            "left join rental_table r on c.id = r.car.id " +
            "where (r.rentalBegin is null or r.rentalBegin is not null) and c.id = ?1 ")
    Optional<Car> getCarToRent(long carId);
}
