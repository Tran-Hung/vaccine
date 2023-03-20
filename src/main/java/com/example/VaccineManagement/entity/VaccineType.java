package com.example.VaccineManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine_type")
public class VaccineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String vaccineTypeId;
    private String name;

}
