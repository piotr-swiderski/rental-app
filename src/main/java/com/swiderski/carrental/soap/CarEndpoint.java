package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarService;
import com.swiderski.rental_service.Car;
import com.swiderski.rental_service.CarList;
import com.swiderski.rental_service.CarListRequest;
import com.swiderski.rental_service.CarObject;
import com.swiderski.rental_service.CarRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CarEndpoint {

    private final CarService carService;
    private final CarWebMapper carWebMapper;

    private static final String NAMESPACE_URI = "http://swiderski.com/rental-service";

    public CarEndpoint(CarService carService, CarWebMapper carWebMapper) {
        this.carService = carService;
        this.carWebMapper = carWebMapper;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarRequest")
    @ResponsePayload
    public CarObject getCar(@RequestPayload CarRequest getCarRequest) {
        CarDto car = carService.getById(getCarRequest.getId());
        return carWebMapper.toCarWebObj(car);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarObject")
    @ResponsePayload
    public CarObject addCar(@RequestPayload CarObject addCarRequest) {

        CarDto carDto = carWebMapper.toDto(addCarRequest);
        CarDto save = carService.save(carDto);
        return carWebMapper.toCarWebObj(save);

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarListRequest")
    @ResponsePayload
    public CarList addCar(@RequestPayload CarListRequest request) {

        List<CarDto> allWithoutSpec = carService.getAllWithoutSpec(request.getPageNo(), request.getPageSize(), request.getSortBy());
        List<Car> cars = carWebMapper.toCarWebList(allWithoutSpec);

        CarList carList = new CarList();
        carList.getCar().addAll(cars);

        return carList;
    }

}
