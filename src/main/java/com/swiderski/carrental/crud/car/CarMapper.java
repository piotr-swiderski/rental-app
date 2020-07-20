package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper extends CommonMapper<Car, CarDto> {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
}
