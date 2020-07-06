package com.swiderski.carrental.dto;

import com.swiderski.carrental.entity.Rental;
import com.swiderski.carrental.mapper.RentalMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.swiderski.carrental.utils.Utils.getRental;
import static com.swiderski.carrental.utils.Utils.getRentalDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RentalMapperTest {

    RentalMapper rentalMapper = RentalMapper.INSTANCE;

    @Test
    public void shouldMapRentalToRentalDto() {
        //given
        Rental rental = getRental();
        RentalDto rentalDto = getRentalDto();
        //when
        RentalDto rentalDtoMapper = rentalMapper.rentalToRentalDto(rental);
        //then
        assertEquals(rentalDto, rentalDtoMapper);
    }

    @Test
    public void shouldMapRentalDtoToRental() {
        //given
        RentalDto rentalDto = getRentalDto();
        Rental rental = getRental();
        //when
        Rental rentalMapped = rentalMapper.rentalDtoToRental(rentalDto);
        //then
        assertEquals(rental, rentalMapped);
    }

    @Test
    public void shouldAssertFalseWhenMapRentalDtoToRental() {
        //given
        RentalDto rentalDto = getRentalDto();
        Rental rental = getRental();
        rental.setRentalBegin(LocalDate.of(2000, 1, 1));
        //when
        Rental rentalMapped = rentalMapper.rentalDtoToRental(rentalDto);
        //then
        assertNotEquals(rental, rentalMapped);
    }

    @Test
    public void shouldAssertFalseWhenMapRentalToRentalDto() {
        //given
        RentalDto rentalDto = getRentalDto();
        Rental rental = getRental();
        rental.setRentalBegin(LocalDate.of(2000, 1, 1));
        //when
        RentalDto rentalDtoMapped = rentalMapper.rentalToRentalDto(rental);
        //then
        assertNotEquals(rentalDto, rentalDtoMapped);
    }
}
