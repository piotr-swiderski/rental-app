package com.swiderski.carrental.soap.car;


import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.crud.car.CarService;
import com.swiderski.carrental.soap.SoapEndpoint;
import com.swiderski.carrental.soap.SoapService;
import com.swiderski.rental_service.schema.car.Car;
import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarDeleteRequest;
import com.swiderski.rental_service.schema.car.CarList;
import com.swiderski.rental_service.schema.car.CarListRequest;
import com.swiderski.rental_service.schema.car.CarRequest;
import com.swiderski.rental_service.schema.car.CarSOAP;
import com.swiderski.rental_service.schema.car.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

@Service
@SoapEndpoint(publish = "/car")
@WebService(endpointInterface = "com.swiderski.rental_service.schema.car.CarSOAP",
        serviceName = "CarSoapService",
        targetNamespace = "http://swiderski.com/rental-service/schema/car",
        portName = "CarSOAP")
@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class CarSoapService implements CarSOAP, SoapService {

    @Autowired
    private CarService carService;
    @Autowired
    private CarWebMapper carWebMapper;
    private final ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public CarList getAllCars(CarListRequest request) {
        PageRequest pageable = carWebMapper.toPageRequest(request.getPageRequest());
        CarParam carParam = carWebMapper.toCarParam(request.getCarFilter());

        Page<CarDto> page = carService.getAll(carParam, pageable);
        PageableXml webPage = carWebMapper.toWebPageable(page);

        CarList carList = objectFactory.createCarList();
        carList.setPage(webPage);

        return carList;
    }

    @Override
    public Car deleteCar(CarDeleteRequest getCarRequest) {
        long carId = getCarRequest.getId();

        CarDto deletedCar = carService.delete(carId);
        CarData carData = carWebMapper.toWebData(deletedCar);

        Car car = objectFactory.createCar();
        car.setCar(carData);

        return car;
    }

    @Override
    public Car getCar(CarRequest getCarRequest) {
        long carId = getCarRequest.getId();

        CarDto car = carService.getById(carId);

        Car carResponse = objectFactory.createCar();
        CarData carData = carWebMapper.toWebData(car);
        carResponse.setCar(carData);

        return carResponse;
    }

    @Override
    public Car addCar(Car addCarRequest) {
        CarData carData = addCarRequest.getCar();

        CarDto carDto = carWebMapper.toDto(carData);
        CarDto savedCar = carService.save(carDto);

        Car car = objectFactory.createCar();
        CarData carDataResponse = carWebMapper.toWebData(savedCar);
        car.setCar(carDataResponse);

        return car;
    }
}
