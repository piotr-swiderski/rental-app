package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CommonRepository<Car>, RevisionRepository<Car, Long, Integer> {
}
