package com.nick.springbootrest.controller;

import com.nick.springbootrest.dto.EquipmentDTO;
import com.nick.springbootrest.mapper.EquipmentMapper;
import com.nick.springbootrest.model.Equipment;
import com.nick.springbootrest.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eq")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final EquipmentMapper mapper;

    public EquipmentController(EquipmentService equipmentService, EquipmentMapper mapper) {
        this.equipmentService = equipmentService;
        this.mapper = mapper;
    }

    @Autowired

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Equipment> equipmentList() {
        return equipmentService.findAllEquipments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Equipment equipment(@PathVariable Long id) {
        return equipmentService.findEquipmentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment createEquipment(@RequestBody @Validated EquipmentDTO equipmentDTO,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ExpressionException("Invalid data: " + bindingResult.getAllErrors());

        return equipmentService.saveEquipment(mapper.toEntity(equipmentDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEquipment(@PathVariable Long id,
                              @RequestBody @Validated EquipmentDTO equipmentDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ExpressionException("Invalid data: " + bindingResult.getAllErrors());

        Equipment equipment = equipmentService.findEquipmentById(id);

        if (!equipmentDTO.getId().equals(equipment.getId()))
            throw new ExpressionException("ID doesn't match");

        equipmentService.updateEquipment(mapper.toEntity(equipmentDTO));
    }
}