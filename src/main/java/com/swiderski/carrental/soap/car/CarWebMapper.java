package com.swiderski.carrental.soap.car;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.soap.abstraction.CommonWebMapper;
import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarFilter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarWebMapper extends CommonWebMapper<CarDto, CarData> {

    CarParam toCarParam(CarFilter carFilter);

}
