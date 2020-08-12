package com.swiderski.carrental.crud.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalParam {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate rentedFrom;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate rentedTo;

    private String hasBrand;

}
