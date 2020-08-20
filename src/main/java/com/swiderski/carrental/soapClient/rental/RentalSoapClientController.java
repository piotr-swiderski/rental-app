package com.swiderski.carrental.soapClient.rental;

import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.crud.rental.RentalParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

import static com.swiderski.carrental.soapClient.MessageUtils.ID_VALID_MESSAGE;

@RestController
@RequestMapping("v1/soap/rentals")
public class RentalSoapClientController extends AbstractSoapClientController<RentalDto, RentalSoapClient, RentalParam> {


    private final RentalSoapClient rentalClientProxy;

    public RentalSoapClientController(RentalSoapClient rentalClientProxy) {
        super(rentalClientProxy);
        this.rentalClientProxy = rentalClientProxy;
    }


    @PostMapping("/return")
    public RentalDto returnCar(@PositiveOrZero(message = ID_VALID_MESSAGE) long rentalId,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate rentalEnd) {
        return rentalClientProxy.returnCar(rentalId, rentalEnd);
    }

    @PostMapping("/rent")
    public RentalDto rentCar(@PositiveOrZero(message = ID_VALID_MESSAGE) long carId,
                             @PositiveOrZero(message = ID_VALID_MESSAGE) long clientId) {
        return rentalClientProxy.rentCar(carId, clientId);
    }

}
