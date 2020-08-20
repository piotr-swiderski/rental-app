package com.swiderski.carrental.soap.car;

import com.swiderski.carrental.soap.SoapEndpoint;
import com.swiderski.carrental.soap.SoapService;
import com.swiderski.rental_service.schema.car.Car;
import com.swiderski.rental_service.schema.car.CarClient;
import com.swiderski.rental_service.schema.car.CarDeleteRequest;
import com.swiderski.rental_service.schema.car.CarList;
import com.swiderski.rental_service.schema.car.CarListRequest;
import com.swiderski.rental_service.schema.car.CarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jws.WebService;

@Controller
@SoapEndpoint(publish = "/car")
@WebService(endpointInterface = "com.swiderski.rental_service.schema.car.CarClient")
public class CarSoapController implements CarClient, SoapService {

    @Autowired
    private CarSoapService carSoapService;

    @Override
    public CarList getAllCars(CarListRequest in) {
        return carSoapService.getAll(in);
    }

    @Override
    public Car deleteCar(CarDeleteRequest in) {
        return carSoapService.delete(in);
    }

    @Override
    public Car getCar(CarRequest in) {
        return carSoapService.get(in);
    }

    @Override
    public Car addCar(Car in) {
        return carSoapService.add(in);
    }
}
