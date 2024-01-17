package com.nick.springbootrest.service;

import com.nick.springbootrest.model.Equipment;
import com.nick.springbootrest.model.Storage;

import java.util.List;

public interface EquipmentService {
    List<Equipment> findAllEquipments();

    Equipment findEquipmentById(Long id);

    Equipment saveEquipment(Equipment equipment);

    void deleteEquipment(Long id);

    void updateEquipment(Equipment equipment);
}