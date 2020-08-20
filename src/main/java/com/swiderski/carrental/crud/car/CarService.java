package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonService;
import com.swiderski.carrental.mail.MailSenderConfigurer;

import java.util.concurrent.CompletableFuture;

public interface CarService extends CommonService<CarDto, CarParam> {
    void sendPdfEmail(MailSenderConfigurer mailSenderConfigurer, CarParam carParam);
}
