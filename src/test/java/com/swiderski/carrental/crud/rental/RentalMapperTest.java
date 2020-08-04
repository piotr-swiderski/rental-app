package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.car.CarMapper;
import com.swiderski.carrental.crud.client.ClientMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static com.swiderski.carrental.utils.Utils.getRental;
import static com.swiderski.carrental.utils.Utils.getRentalDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class RentalMapperTest {

    @InjectMocks
    RentalMapper rentalMapper = RentalMapperImpl.INSTANCE;

    @Before
    public void setUp() {
        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class); // Initialization of the mapper
        ReflectionTestUtils.setField(rentalMapper, "clientMapper", clientMapper);
        CarMapper carMapper = Mappers.getMapper(CarMapper.class); // Initialization of the mapper
        ReflectionTestUtils.setField(rentalMapper, "carMapper", carMapper);
    }

    @Test
    public void shouldMapRentalToRentalDto() {
        //given
        Rental rental = getRental();
        RentalDto rentalDto = getRentalDto();
        //when
        RentalDto rentalDtoMapper = rentalMapper.toDto(rental);
        //then
        assertEquals(rentalDto, rentalDtoMapper);
    }

    @Test
    public void shouldMapRentalDtoToRental() {
        //given
        RentalDto rentalDto = getRentalDto();
        Rental rental = getRental();
        //when
        Rental rentalMapped = rentalMapper.fromDto(rentalDto);
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
        Rental rentalMapped = rentalMapper.fromDto(rentalDto);
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
        RentalDto rentalDtoMapped = rentalMapper.toDto(rental);
        //then
        assertNotEquals(rentalDto, rentalDtoMapped);
    }
}
