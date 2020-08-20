package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.AbstractController;
import com.swiderski.carrental.mail.MailSenderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/cars"})
public class CarController extends AbstractController<CarService, CarDto, CarParam> {

    private final CarService carService;


    @Autowired
    public CarController(CarService service) {
        super(service);
        this.carService = service;
    }

    @GetMapping("/mail")
    public String sendMail(@ModelAttribute MailSenderConfigurer mailSenderConfigurer, CarParam carParam) {
        carService.sendPdfEmail(mailSenderConfigurer, carParam);
        System.out.println("ddd");
        return "Send mail";
    }

}
