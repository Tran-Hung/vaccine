package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.common.SecureRandomUtil;
import com.example.VaccineManagement.entity.VaccineType;
import com.example.VaccineManagement.repository.VaccineTypeRepository;
import com.example.VaccineManagement.service.VaccineTypeService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccineTypeServiceImpl implements VaccineTypeService {

    @Autowired
    VaccineTypeRepository vaccineTypeRepository;

    @Override
    public void createVaccineType(String name) {
        String id = "";
        int length = 4;
        boolean useLetters = true;
        boolean useNumbers = true;
        boolean isExisted = true;
        while (isExisted) {
            id = RandomStringUtils.random(length, useLetters, useNumbers).toUpperCase();
            long count = vaccineTypeRepository.countAllByVaccineTypeId(id);
            if (count == 0) isExisted = false;
        }
        VaccineType vaccineType = new VaccineType();
        vaccineType.setVaccineTypeId(id);
        vaccineType.setName(name);
        vaccineTypeRepository.save(vaccineType);

    }

    @Override
    public VaccineType findVaccineType(String name) {
        return vaccineTypeRepository.findByName(name);
    }
}
