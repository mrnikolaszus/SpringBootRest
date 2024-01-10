package com.nick.springbootrest.mapper;

import com.nick.springbootrest.dto.EquipmentDTO;
import com.nick.springbootrest.model.Equipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper implements Mapper<Equipment, EquipmentDTO> {
    public Equipment toEntity(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setId(equipmentDTO.getId());
        equipment.setName(equipmentDTO.getName());
        equipment.setDescription(equipmentDTO.getDescription());
        equipment.setPrice(equipmentDTO.getPrice());
        equipment.setCategory(equipmentDTO.getCategory());

        equipment.setTimeCreated(equipmentDTO.getTimeCreated());
        equipment.setTimeUpdated(equipmentDTO.getTimeUpdated());

        return equipment;
    }

    public EquipmentDTO toDTO(Equipment equipment) {
        return EquipmentDTO.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .description(equipment.getDescription())
                .price(equipment.getPrice())
                .category(equipment.getCategory())
                .timeCreated(equipment.getTimeCreated())
                .timeUpdated(equipment.getTimeUpdated())
                .build();
    }
}