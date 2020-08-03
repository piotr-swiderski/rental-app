package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService extends CommonService<CarDto> {

    Page<CarDto> getAll(CarParam carParam, Pageable pageable);

    List<CarDto> getAllWithoutSpec(int pageNo, int pageSize, String sortBy);
}
