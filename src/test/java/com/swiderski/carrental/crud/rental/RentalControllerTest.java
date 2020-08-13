package com.swiderski.carrental.crud.rental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiderski.carrental.crud.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.swiderski.carrental.utils.Utils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@WebMvcTest(controllers = RentalController.class)
@AutoConfigureMockMvc(addFilters = false)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RentService rentService;

    @Test
    void getAll_shouldReturn200() throws Exception {

        when(rentService.getAll(any(RentalParam.class), any(PageRequest.class))).thenReturn(getRentalPage());

        mockMvc.perform(get("/v1/rentals")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andReturn();
    }

    @Test
    void getById_shouldReturnRentalAndStatusIs200() throws Exception {

        when(rentService.getById(1)).thenReturn(getRentalDto());

        mockMvc.perform(get("/v1/rentals/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rentalId))
                .andExpect(jsonPath("$.car.brand").value(carBrand))
                .andReturn();
    }

    @Test
    void getById_shouldThrowExceptionAndStatusIs404() throws Exception {

        when(rentService.getById(1)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/v1/rentals/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void save_shouldReturnRentalAndStatusIs200() throws Exception {

        when(rentService.save(any(RentalDto.class))).thenReturn(getRentalDto());

        mockMvc.perform(post("/v1/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getRentalDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rentalId))
                .andExpect(jsonPath("$.car.brand").value(carBrand))
                .andReturn();

    }

    @Test
    void delete_shouldDeleteRentalAndStatusIs200() throws Exception {

        when(rentService.delete(1)).thenReturn(getRentalDto());

        mockMvc.perform(delete("/v1/rentals/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rentalId))
                .andExpect(jsonPath("$.car.brand").value(carBrand))
                .andReturn();

    }

    @Test
    void update_shouldUpdateRentalAndStatusIs200() throws Exception {

        when(rentService.update(anyLong(), any(RentalDto.class))).thenReturn(getRentalDto());

        mockMvc.perform(put("/v1/rentals/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getRentalDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rentalId))
                .andExpect(jsonPath("$.car.brand").value(carBrand))
                .andReturn();

    }

    @Test
    void rentCar_shouldRentCar() throws Exception {

        final long CAR_ID = 1L;
        final long CLIENT_ID = 1L;
        when(rentService.rentCar(CAR_ID, CLIENT_ID)).thenReturn(getRentalDto());

        mockMvc.perform(post("/v1/rentals/rent")
                .param("carId", String.valueOf(CAR_ID))
                .param("clientId", String.valueOf(CLIENT_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rentalId))
                .andReturn();
    }

    @Test
    void returnCar_shouldReturnCar() throws Exception {

        final long RENTAL_ID = 0L;
        final LocalDate RETURNED_DATE = rentalEnd;
        when(rentService.returnCar(RENTAL_ID, RETURNED_DATE)).thenReturn(getRentalDto());

        mockMvc.perform(post("/v1/rentals/return")
                .contentType(MediaType.APPLICATION_JSON)
                .param("rentalId", String.valueOf(RENTAL_ID))
                .param("returnedDate", String.valueOf(RETURNED_DATE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rentalId))
                .andExpect(jsonPath("$.rentalEnd").value(RETURNED_DATE.toString()))
                .andReturn();
    }

    @Test
    void getAvailableCars_shouldReturnedPageOfAvailableCars() throws Exception {

        when(rentService.getAvailableCars(pageNo, pageSize, sortBy)).thenReturn(getCarPage());

        mockMvc.perform(get("/v1/rentals/availableCars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(clientId))
                .andExpect(jsonPath("$.content[0].brand").value(carBrand))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andReturn();

    }


    @Test
    void getRentedCars_shouldReturnedPageOfRentedCars() throws Exception {

        when(rentService.getRentedCars(pageNo, pageSize, sortBy)).thenReturn(getCarPage());

        mockMvc.perform(get("/v1/rentals/rentedCars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(carId))
                .andExpect(jsonPath("$.content[0].brand").value(carBrand))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andReturn();

    }
}