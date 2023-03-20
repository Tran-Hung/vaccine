package com.example.VaccineManagement.service;

import com.example.VaccineManagement.entity.VaccineType;

public interface VaccineTypeService{
    /**
     * TinVT
     * create vaccineType
     * @return
     */
    void createVaccineType(String name);

    /**
     * TinVT
     * find vaccineType by name
     * @return
     */
    VaccineType findVaccineType(String name);
}
