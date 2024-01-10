package com.nick.springbootrest.service;

import com.nick.springbootrest.model.Storage;

import java.util.List;

public interface EquipmentService {
    List<Storage> findAllEquipment();

    Storage findEquipmentById(Long id);

    Storage saveEquipment(Storage equipment);

    void deleteEquipment(Long id);

    void updateEquipment(Storage equipment);
}