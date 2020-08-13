package com.swiderski.carrental.crud.car;

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

import static com.swiderski.carrental.utils.Utils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@WebMvcTest(controllers = CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    @Test
    void getAll_shouldReturn200() throws Exception {

        when(carService.getAll(any(CarParam.class), any(PageRequest.class))).thenReturn(getCarPage());

        mockMvc.perform(get("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].brand").value(carBrand))
                .andReturn();
    }

    @Test
    void getById_shouldReturnCarAndStatusIs200() throws Exception {

        when(carService.getById(1)).thenReturn(getCarDto());

        mockMvc.perform(get("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carId))
                .andExpect(jsonPath("$.brand").value(carBrand))
                .andReturn();
    }

    @Test
    void getById_shouldThrowExceptionAndStatusIs404() throws Exception {

        when(carService.getById(1)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void save_shouldReturnCarsAndStatusIs200() throws Exception {

        when(carService.save(any(CarDto.class))).thenReturn(getCarDto());

        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getCarDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carId))
                .andExpect(jsonPath("$.brand").value(carBrand))
                .andExpect(jsonPath("$.model").value(carModelName))
                .andReturn();

    }

    @Test
    void delete_shouldDeleteCarAndStatusIs200() throws Exception {

        when(carService.delete(1)).thenReturn(getCarDto());

        mockMvc.perform(delete("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carId))
                .andExpect(jsonPath("$.brand").value(carBrand))
                .andExpect(jsonPath("$.model").value(carModelName))
                .andReturn();

    }

    @Test
    void update_shouldUpdateCarAndStatusIs200() throws Exception {

        when(carService.update(anyLong(), any(CarDto.class))).thenReturn(getCarDto());

        mockMvc.perform(put("/v1/cars/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getCarDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carId))
                .andExpect(jsonPath("$.brand").value(carBrand))
                .andReturn();
    }

}