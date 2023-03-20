package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.entity.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineTypeRepository  extends JpaRepository<VaccineType, Long> {

    VaccineType findByName(String name);

    long countAllByVaccineTypeId(String provideId);
}
