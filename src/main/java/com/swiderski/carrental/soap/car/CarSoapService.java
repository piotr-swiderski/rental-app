package com.swiderski.carrental.soap.car;

import com.swiderski.rental_service.schema.car.Car;
import com.swiderski.rental_service.schema.car.CarDeleteRequest;
import com.swiderski.rental_service.schema.car.CarList;
import com.swiderski.rental_service.schema.car.CarListRequest;
import com.swiderski.rental_service.schema.car.CarRequest;

public interface CarSoapService {
    CarList getAll(CarListRequest request);

    Car delete(CarDeleteRequest getCarRequest);

    Car get(CarRequest getCarRequest);

    Car add(Car addCarRequest);

}
