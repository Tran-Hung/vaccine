package com.example.VaccineManagement.bean;

import lombok.Data;

import java.util.Date;

@Data
public class VaccineOrmBean {
    private String id;
    private String name;
    private String vaccineType;
    private String dayReceive;
    private String licenseCode;
    private String origin;
    private Double dosage;
    private Date expired;
    private String maintenance;
    private String age;
    private Long quantity;
    private Long times;
    private Long duration;


    public VaccineOrmBean(String id, String name, String vaccineType, String dayReceive, String licenseCode, String origin, Double dosage, Date expired, String maintenance, String age, Long quantity, Long times, Long duration) {
        this.id = id;
        this.name = name;
        this.vaccineType = vaccineType;
        this.dayReceive = dayReceive;
        this.licenseCode = licenseCode;
        this.origin = origin;
        this.dosage = dosage;
        this.expired = expired;
        this.maintenance = maintenance;
        this.age = age;
        this.quantity = quantity;
        this.times = times;
        this.duration = duration;
    }
}
