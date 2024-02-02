package com.nick.springbootrest.service.impl;

import com.nick.springbootrest.exception.EquipmentNotFoundException;
import com.nick.springbootrest.model.Equipment;
import com.nick.springbootrest.repository.EquipmentRepository;
import com.nick.springbootrest.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository repository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Equipment> findAllEquipments() {
        List<Equipment> equipmentList = repository.findAll();
        return equipmentList;
    }

    @Override
    public Equipment findEquipmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id [" + id + "]"));
    }

    @Override
    @Transactional
    public Equipment saveEquipment(Equipment equipment) {
        return repository.save(equipment);
    }

    @Override
    @Transactional
    public void updateEquipment(Equipment equipment) {
        repository.save(equipment);
    }

    @Override
    @Transactional
    public void deleteEquipment(Long id) {
        Equipment equipment = repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id [" + id + "]"));
        repository.delete(equipment);
    }
}