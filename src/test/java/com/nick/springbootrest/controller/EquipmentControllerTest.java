package com.nick.springbootrest.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nick.springbootrest.dto.EquipmentDTO;
import com.nick.springbootrest.mapper.EquipmentMapper;
import com.nick.springbootrest.model.Equipment;
import com.nick.springbootrest.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EquipmentController.class)
class EquipmentControllerTest {

    private final String BASE_URL = "/api/eq";
    private final String BASE_URL_ID = "/api/eq/{id}";

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private EquipmentMapper mapper;

    @MockBean
    private EquipmentService equipmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void EquipmentList() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(equipmentService, times(1)).findAllEquipments();
    }

    @Test
    void Equipment() throws Exception {
        long id = 4;
        EquipmentDTO equipmentDTO = EquipmentDTO.builder()
                .id(id)
                .name("Test")
                .build();
        Equipment Equipment = mapper.toEntity(equipmentDTO);
        String EquipmentJSON = objectMapper.writeValueAsString(equipmentDTO);

        when(equipmentService.findEquipmentById(id)).thenReturn(Equipment);

        mockMvc.perform(get(BASE_URL_ID, id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(EquipmentJSON));

        verify(equipmentService, times(1)).findEquipmentById(id);
    }

    @Test
    void createEquipment() throws Exception {
        EquipmentDTO equipmentDTO = EquipmentDTO.builder()
                .name("Test")
                .build();
        Equipment Equipment = mapper.toEntity(equipmentDTO);
        String EquipmentJSON = objectMapper.writeValueAsString(equipmentDTO);

        when(equipmentService.saveEquipment(Mockito.any(Equipment.class))).thenReturn(Equipment);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EquipmentJSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(EquipmentJSON));

        verify(equipmentService, times(1)).saveEquipment(Mockito.any(Equipment.class));
    }

    @Test
    void deleteEquipment() throws Exception {
        long id = 4;

        mockMvc.perform(delete(BASE_URL_ID, id))
                .andExpect(status().isOk());

        verify(equipmentService, times(1)).deleteEquipment(id);
    }

    @Test
    void updateEquipment() throws Exception {
        long id = 4;
        EquipmentDTO equipmentDTO = EquipmentDTO.builder()
                .id(id)
                .name("Test")
                .build();
        Equipment Equipment = mapper.toEntity(equipmentDTO);
        String EquipmentJSON = objectMapper.writeValueAsString(equipmentDTO);

        when(equipmentService.findEquipmentById(id)).thenReturn(Equipment);

        mockMvc.perform(put(BASE_URL_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EquipmentJSON))
                .andExpect(status().isOk());

        verify(equipmentService, times(1)).findEquipmentById(id);
        verify(equipmentService, times(1)).updateEquipment(Mockito.any(Equipment.class));
    }

}