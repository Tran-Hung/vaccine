package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.entity.Patient;
import com.example.VaccineManagement.repository.PatientRepository;
import com.example.VaccineManagement.service.PatientService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Page<Patient> findAllPatient2(Pageable pageable) {
        return null;
    }

    @Override
    public Patient findPatientById(int id) {
        return null;
    }

    @Override
    public Page<Patient> search(String name, String id, int pageable) {
        return null;
    }

    @Override
    public void editPatient(Patient patient) {

    }

    @Override
    public void addPatient(String name, String dateOfBirth, String gender, String guardian, String phone, String address, String email, String accountId, Boolean deleteFlag) {
        String id = "";
        boolean isExisted = true;
        while (isExisted) {
            id = RandomString.make(4).toUpperCase();
            long count = patientRepository.countByPatientId(id);
            if (count == 0) isExisted = false;
        }
        patientRepository.savePatientToRegister(id, name, dateOfBirth, gender, guardian, phone, address, email, accountId, deleteFlag);
    }

    @Override
    public Patient create(Patient patientTemp) {
        return null;
    }

    @Override
    public Integer findByEmail(String email) {
        return null;
    }

    @Override
    public Integer findByPhone(String phone) {
        return null;
    }

    @Override
    public List<Patient> findAllByEmail(String email, boolean b) {
        return null;
    }

    @Override
    public Patient findByAccountId(String id, boolean b) {
        return patientRepository.findByAccountIdAndDeleteFlag(id, b);
    }

    @Override
    public Patient getPatientById(Integer patientId) {
        return null;
    }
}
