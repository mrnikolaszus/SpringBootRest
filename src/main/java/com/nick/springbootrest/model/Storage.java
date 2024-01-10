package com.nick.springbootrest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "storage", schema = "eq_api")
public class Storage extends BaseEntity {

    @Column(name = "name", length = 128, nullable = false)
    private String name;
}