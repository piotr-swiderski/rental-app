package com.swiderski.carrental.car;

import com.swiderski.carrental.abstraction.CommonMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper extends CommonMapper<Car, CarDto> {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
}
