package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonService;

import java.util.List;

public interface CarService extends CommonService<CarDto> {

    List<CarDto> getAllWithoutSpec(int pageNo, int pageSize, String sortBy);

}
