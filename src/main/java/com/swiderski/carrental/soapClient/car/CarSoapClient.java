package com.swiderski.carrental.soapClient.car;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.soap.car.CarWebMapper;
import com.swiderski.carrental.soapClient.abstraction.CommonSoapClient;
import com.swiderski.rental_service.schema.car.Car;
import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarDeleteRequest;
import com.swiderski.rental_service.schema.car.CarFilter;
import com.swiderski.rental_service.schema.car.CarList;
import com.swiderski.rental_service.schema.car.CarListRequest;
import com.swiderski.rental_service.schema.car.CarRequest;
import com.swiderski.rental_service.schema.car.CarSOAP;
import com.swiderski.rental_service.schema.car.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageRequestXml;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarSoapClient implements CommonSoapClient<CarDto, CarParam> {

    private final CarSOAP carSoapProxy;
    private final ObjectFactory carObjectFactory;
    private final CarWebMapper carWebMapper;


    public CarSoapClient(@Qualifier("carProxy") CarSOAP carSoapProxy, ObjectFactory carObjectFactory, CarWebMapper carWebMapper) {
        this.carSoapProxy = carSoapProxy;
        this.carObjectFactory = carObjectFactory;
        this.carWebMapper = carWebMapper;
    }

    @Override
    public Page<CarDto> getAll(CarParam carParam, Pageable pageable) {
        CarListRequest carListRequest = carObjectFactory.createCarListRequest();

        CarFilter carFilter = carWebMapper.toCarFiler(carParam);
        PageRequestXml pageableXml = carWebMapper.toPageRequestXml(pageable);
        carListRequest.setCarFilter(carFilter);
        carListRequest.setPageRequest(pageableXml);

        CarList allCars = carSoapProxy.getAllCars(carListRequest);

        List<CarData> carDataList = carWebMapper.toDataList(allCars.getPage().getContent());
        List<CarDto> pageList = carWebMapper.toDtoList(carDataList);
        return new PageImpl<>(pageList, pageable, allCars.getPage().getNumberOfElements());

    }

    @Override
    public CarDto delete(long id) {
        CarDeleteRequest carDeleteRequest = carObjectFactory.createCarDeleteRequest();
        carDeleteRequest.setId(id);

        CarData car = carSoapProxy.deleteCar(carDeleteRequest).getCar();
        return carWebMapper.toDto(car);
    }

    @Override
    public CarDto getById(long id) {
        CarRequest carRequest = carObjectFactory.createCarRequest();
        carRequest.setId(id);

        CarData car = carSoapProxy.getCar(carRequest).getCar();
        return carWebMapper.toDto(car);
    }

    @Override
    public CarDto add(CarDto carDto) {
        CarData carData = carWebMapper.toWebData(carDto);
        Car car = carObjectFactory.createCar();

        car.setCar(carData);
        Car addCar = carSoapProxy.addCar(car);

        CarData addedCarData = addCar.getCar();
        return carWebMapper.toDto(addedCarData);
    }

    @Override
    public CarDto update(CarDto carDto, long id) {
        carDto.setId(id);
        return add(carDto);
    }
}