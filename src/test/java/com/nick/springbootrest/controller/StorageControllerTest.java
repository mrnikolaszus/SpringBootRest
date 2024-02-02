package com.nick.springbootrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nick.springbootrest.dto.StorageDTO;
import com.nick.springbootrest.mapper.StorageMapper;
import com.nick.springbootrest.model.Storage;
import com.nick.springbootrest.service.StorageService;
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

@WebMvcTest(controllers = EquipmentController.class)
class StorageControllerTest {
    private final String BASE_URL = "/api/storage";
    private final String BASE_URL_ID = "/api/storage/{id}";

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private StorageMapper mapper;

    @MockBean
    private StorageService storageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void storageList() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(storageService, times(1)).findAllStorages();
    }

    @Test
    void storage() throws Exception {
        long id = 123;
        StorageDTO storageDTO = StorageDTO.builder()
                .id(id)
                .name("Test")
                .build();
        Storage storage = mapper.toEntity(storageDTO);
        String storageJSON = objectMapper.writeValueAsString(storage);

        when(storageService.findStorageById(id)).thenReturn(storage);

        mockMvc.perform(get(BASE_URL_ID, id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(storageJSON));

        verify(storageService, times(1)).findStorageById(id);
    }

    @Test
    void createStorage() throws Exception {
        StorageDTO storageDTO = StorageDTO.builder()
                .name("Test storage")
                .build();
        Storage storage = mapper.toEntity(storageDTO);
        String storageDTOJson = objectMapper.writeValueAsString(storageDTO);

        when(storageService.saveStorage(Mockito.any(Storage.class)))
                .thenReturn(storage);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storageDTOJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(storageDTOJson));

        verify(storageService, times(1)).saveStorage(Mockito.any(Storage.class));
    }

    @Test
    void deleteStorage() throws Exception {
        long id = 4;

        mockMvc.perform(delete(BASE_URL_ID, id))
                .andExpect(status().isOk());
    }

    @Test
    void updateStorage() throws Exception {
        long id = 4;
        StorageDTO storageDTO = StorageDTO.builder()
                .id(id)
                .name("Test storage")
                .build();
        String storageDTOJson = objectMapper.writeValueAsString(storageDTO);
        Storage storage = mapper.toEntity(storageDTO);

        when(storageService.findStorageById(Mockito.any(Long.class))).thenReturn(storage);

        mockMvc.perform(put(BASE_URL_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storageDTOJson))
                .andExpect(status().isOk());

        verify(storageService, times(1)).findStorageById(id);
        verify(storageService, times(1)).updateStorage(Mockito.any(Storage.class));
    }
}