package com.swiderski.carrental.crud.rental;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.pdfGenerator.utils.ColorEnum;
import com.swiderski.carrental.pdfGenerator.annotation.ColumnRow;
import com.swiderski.carrental.pdfGenerator.annotation.JoinColumns;
import com.swiderski.carrental.pdfGenerator.annotation.PdfTableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

import static com.swiderski.carrental.crud.rental.RentalMessageUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PdfTableName("Rental report")
public class RentalDto extends AbstractDto {

    @NotNull(message = CAR_VALID_MESSAGE)
    @JoinColumns(value = {@ColumnRow(filed = "brand", value = "Car brand", textColor = ColorEnum.GREEN), @ColumnRow(filed = "model", value = "Car model")})
    private CarDto car;

    @NotNull(message = CLIENT_VALID_MESSAGE)
    @JoinColumns(value = {@ColumnRow(filed = "name", value = "Client surname", background = ColorEnum.YELLOW), @ColumnRow(filed = "surname", value = "Client surname")})
    private ClientDto client;

    @NotNull(message = RENTAL_BEGIN_VALID_MESSAGE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalBegin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalEnd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalDto rentalDto = (RentalDto) o;
        return id == rentalDto.id &&
                Objects.equals(car, rentalDto.car) &&
                Objects.equals(client, rentalDto.client) &&
                Objects.equals(rentalBegin, rentalDto.rentalBegin) &&
                Objects.equals(rentalEnd, rentalDto.rentalEnd);
    }

    public RentalDto(Long id, CarDto car, ClientDto client, LocalDate rentalBegin, LocalDate rentalEnd) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.rentalBegin = rentalBegin;
        this.rentalEnd = rentalEnd;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, car, client, rentalBegin, rentalEnd);
    }

    @Override
    public String toString() {
        return "RentalDto{" +
                "car=" + car +
                ", client=" + client +
                ", rentalBegin=" + rentalBegin +
                ", rentalEnd=" + rentalEnd +
                ", id=" + id +
                '}';
    }
}
