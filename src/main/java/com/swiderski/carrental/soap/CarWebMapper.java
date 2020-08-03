package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.rental_service.schema.car.CarData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarWebMapper {

    CarDto toDto(CarData carData);

    CarData toCarData(CarDto carDto);

    List<CarData> toCarDataList(List<CarDto> carDtos);
}
