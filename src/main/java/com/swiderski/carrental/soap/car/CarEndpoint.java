package com.swiderski.carrental.soap.car;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.crud.car.CarService;
import com.swiderski.rental_service.schema.car.Car;
import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarDeleteRequest;
import com.swiderski.rental_service.schema.car.CarList;
import com.swiderski.rental_service.schema.car.CarListRequest;
import com.swiderski.rental_service.schema.car.CarRequest;
import com.swiderski.rental_service.schema.car.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import com.swiderski.rental_service.schema.rental.CarRentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CarEndpoint {

    private final CarService carService;
    private final CarWebMapper carWebMapper;

    private static final String NAMESPACE_URI = "http://swiderski.com/rental-service/schema/car";

    @Autowired
    public CarEndpoint(CarService carService, CarWebMapper carWebMapper) {
        this.carService = carService;
        this.carWebMapper = carWebMapper;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarRequest")
    @ResponsePayload
    public Car getCar(@RequestPayload CarRequest getCarRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        long carId = getCarRequest.getId();

        CarDto car = carService.getById(carId);

        Car carResponse = objectFactory.createCar();
        CarData carData = carWebMapper.toWebData(car);
        carResponse.setCar(carData);

        return carResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarDeleteRequest")
    @ResponsePayload
    public Car deleteCar(@RequestPayload CarDeleteRequest getCarRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        long carId = getCarRequest.getId();

        CarDto deletedCar = carService.delete(carId);
        CarData carData = carWebMapper.toWebData(deletedCar);

        Car car = objectFactory.createCar();
        car.setCar(carData);

        return car;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Car")
    @ResponsePayload
    public Car addCar(@RequestPayload Car addCarRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        CarData carData = addCarRequest.getCar();

        CarDto carDto = carWebMapper.toDto(carData);
        CarDto savedCar = carService.save(carDto);

        Car car = objectFactory.createCar();
        CarData carDataResponse = carWebMapper.toWebData(savedCar);
        car.setCar(carDataResponse);

        return car;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarListRequest")
    @ResponsePayload
    public CarList getCarList(@RequestPayload CarListRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        int pageNo = request.getPageNo();
        int pageSize = request.getPageSize();
        String sortBy = request.getSortBy();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        CarParam carParam = carWebMapper.toCarParam(request.getCarFilter());

        Page<CarDto> page = carService.getAll(carParam, pageable);
        PageableXml webPage = carWebMapper.toWebPageable(page);

        CarList carList = objectFactory.createCarList();
        carList.setPage(webPage);

        return carList;
    }

}
