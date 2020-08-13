package com.swiderski.carrental.crud.client;

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
@WebMvcTest(controllers = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @Test
    void getAll_shouldReturn200() throws Exception {

        when(clientService.getAll(any(ClientParam.class), any(PageRequest.class))).thenReturn(getClientPage());

        mockMvc.perform(get("/v1/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].name").value(clientName))
                .andReturn();
    }

    @Test
    void getById_shouldReturnClientAndStatusIs200() throws Exception {

        when(clientService.getById(1)).thenReturn(getClientDto());

        mockMvc.perform(get("/v1/clients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.name").value(clientName))
                .andReturn();
    }

    @Test
    void getById_shouldThrowExceptionAndStatusIs404() throws Exception {

        when(clientService.getById(1)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/v1/clients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void save_shouldSaveAndReturnClientsAndStatusIs200() throws Exception {

        when(clientService.save(any(ClientDto.class))).thenReturn(getClientDto());

        mockMvc.perform(post("/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getRentalDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.name").value(clientName))
                .andExpect(jsonPath("$.surname").value(clientSurname))
                .andReturn();

    }

    @Test
    void delete_shouldDeleteClientAndStatusIs200() throws Exception {

        when(clientService.delete(1)).thenReturn(getClientDto());

        mockMvc.perform(delete("/v1/clients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.name").value(clientName))
                .andExpect(jsonPath("$.surname").value(clientSurname))
                .andReturn();

    }

    @Test
    void update_shouldUpdateClientAndStatusIs200() throws Exception {

        when(clientService.update(anyLong(), any(ClientDto.class))).thenReturn(getClientDto());

        mockMvc.perform(put("/v1/clients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getClientDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.name").value(clientName))
                .andReturn();
    }

}