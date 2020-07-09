package com.swiderski.carrental.mapper;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto carToCarDto(Car car);

    Car carDtoToCar(CarDto carDto);

    Set<CarDto> carSetToCarDtoSet(Set<Car> carSet);

    Collection<CarDto> carCollectionToCarDtoCollection(Collection<Car> cars);

    List<CarDto> carListToCarDtoList(List<Car> cars);
}
