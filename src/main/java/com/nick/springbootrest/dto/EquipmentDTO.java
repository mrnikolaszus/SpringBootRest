package com.nick.springbootrest.dto;

import com.nick.springbootrest.model.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class EquipmentDTO {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}