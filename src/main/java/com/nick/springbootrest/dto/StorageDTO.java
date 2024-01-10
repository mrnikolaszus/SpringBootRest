package com.nick.springbootrest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StorageDTO {
    private Long id;
    private String name;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}