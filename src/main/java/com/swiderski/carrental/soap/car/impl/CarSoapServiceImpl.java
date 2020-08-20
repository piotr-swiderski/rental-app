package com.swiderski.carrental.soap.car.impl;


import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.crud.car.CarService;
import com.swiderski.carrental.soap.car.CarSoapService;
import com.swiderski.carrental.soap.car.CarWebMapper;
import com.swiderski.rental_service.schema.car.Car;
import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarDeleteRequest;
import com.swiderski.rental_service.schema.car.CarList;
import com.swiderski.rental_service.schema.car.CarListRequest;
import com.swiderski.rental_service.schema.car.CarRequest;
import com.swiderski.rental_service.schema.car.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CarSoapServiceImpl implements CarSoapService {

    private final CarService carService;
    private final CarWebMapper carWebMapper;
    private final ObjectFactory objectFactory = new ObjectFactory();

    public CarSoapServiceImpl(CarService carService, CarWebMapper carWebMapper) {
        this.carService = carService;
        this.carWebMapper = carWebMapper;
    }

    @Override
    public CarList getAll(CarListRequest request) {
        PageRequest pageable = carWebMapper.toPageRequest(request.getPageRequest());
        CarParam carParam = carWebMapper.toCarParam(request.getCarFilter());

        Page<CarDto> page = carService.getAll(carParam, pageable);
        PageableXml webPage = carWebMapper.toWebPageable(page);

        CarList carList = objectFactory.createCarList();
        carList.setPage(webPage);

        return carList;
    }

    @Override
    public Car delete(CarDeleteRequest getCarRequest) {
        long carId = getCarRequest.getId();

        CarDto deletedCar = carService.delete(carId);
        CarData carData = carWebMapper.toWebData(deletedCar);

        Car car = objectFactory.createCar();
        car.setCar(carData);

        return car;
    }

    @Override
    public Car get(CarRequest getCarRequest) {
        long carId = getCarRequest.getId();

        CarDto car = carService.getById(carId);

        Car carResponse = objectFactory.createCar();
        CarData carData = carWebMapper.toWebData(car);
        carResponse.setCar(carData);

        return carResponse;
    }

    @Override
    public Car add(Car addCarRequest) {
        CarData carData = addCarRequest.getCar();

        CarDto carDto = carWebMapper.toDto(carData);
        CarDto savedCar = carService.save(carDto);

        Car car = objectFactory.createCar();
        CarData carDataResponse = carWebMapper.toWebData(savedCar);
        car.setCar(carDataResponse);

        return car;
    }
}
