package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonRepository;
import org.springframework.data.history.Revision;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends CommonRepository<Car>, RevisionRepository<Car, Long, Integer> {
    @Query("SELECT c FROM car_table c WHERE c.modifiedDate >= ?1")
    List<Car> findLastedUpdateCars(LocalDateTime modifiedDate);


    @Query(
            value = "SELECT * FROM car_table_aud c GROUP BY c.id having max(c.rev)",
            nativeQuery = true)
    Revision<Integer, Car> findAllLastUpdated();

}
