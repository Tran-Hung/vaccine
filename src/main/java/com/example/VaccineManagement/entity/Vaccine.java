package com.example.VaccineManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String vaccineId;
    private String name;
    private String licenseCode;
    private String origin;
    private String age;
    private String maintenance;
    private String image;
    private Double dosage;
    @Column(columnDefinition = "Date")
    private String expired;
    private Long quantity;
    private Boolean deleteFlag;
    private Integer times;
    private Integer duration;
    private String vaccineType;

}
