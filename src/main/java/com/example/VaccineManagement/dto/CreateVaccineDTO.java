package com.example.VaccineManagement.dto;

import lombok.Data;

@Data
public class CreateVaccineDTO {
    private String imgVaccine;
    private String nameVaccine;
    private String typeVaccine;
    private String dayReceive;
    private String licenseCode;
    private String origin;
    private String provider;
    private int unitPrice;
    private double dosage;
    private long quantity;
    private String expired;
    private String maintenance;
    private String age;
    private int times;
    private int duration;
}
