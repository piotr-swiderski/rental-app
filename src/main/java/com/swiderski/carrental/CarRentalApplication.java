package com.swiderski.carrental;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }
    //todo Specialization (Do kazdej encji obiekt z zestawem filtrow)
    //todo Docker (Stworzyc dockera wraz z auth serverem i apka)
}
