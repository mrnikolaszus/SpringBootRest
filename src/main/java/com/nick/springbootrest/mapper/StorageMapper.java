package com.nick.springbootrest.mapper;

import com.nick.springbootrest.dto.StorageDTO;
import com.nick.springbootrest.model.Storage;
import org.springframework.stereotype.Component;

@Component
public class StorageMapper implements Mapper<Storage, StorageDTO> {
    public Storage toEntity(StorageDTO storageDTO) {
        Storage Storage = new Storage();
        Storage.setId(storageDTO.getId());
        Storage.setName(storageDTO.getName());
        Storage.setTimeCreated(storageDTO.getTimeCreated());
        Storage.setTimeUpdated(storageDTO.getTimeUpdated());

        return Storage;
    }

    public StorageDTO toDTO(Storage storage) {
        return StorageDTO.builder()
                .id(storage.getId())
                .name(storage.getName())
                .timeCreated(storage.getTimeCreated())
                .timeUpdated(storage.getTimeUpdated())
                .build();
    }
}