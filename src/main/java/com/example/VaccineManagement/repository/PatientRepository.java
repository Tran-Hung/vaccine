package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into patient(patient_id,name,date_of_birth,gender,guardian,phone,address,email,account_id,delete_flag) values (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
    void savePatientToRegister(String id, String name, String dateOfBirth, String gender, String guardian, String phone, String address, String email, String accountId, Boolean deleteFlag);

    Patient findByAccountIdAndDeleteFlag(String id, Boolean flag);

    long countByPatientId(String id);
}
