package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends CommonRepository<Car> {

    @Query("SELECT c FROM car_table c WHERE c.modifiedDate >= ?1")
    List<Car> findLastedUpdateCars(LocalDateTime modifiedDate);

}
