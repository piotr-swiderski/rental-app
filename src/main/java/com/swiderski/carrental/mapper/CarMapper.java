package com.swiderski.carrental.mapper;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto carToCarDto(Car car);

    Car carDtoToCar(CarDto carDto);

    Set<CarDto> carSetToCarDtoSet(Set<Car> carSet);

}
