package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.common.SecureRandomUtil;
import com.example.VaccineManagement.dto.CreateVaccineDTO;
import com.example.VaccineManagement.dto.VaccineDTO;
import com.example.VaccineManagement.entity.Vaccine;
import com.example.VaccineManagement.repository.VaccineRepository;
import com.example.VaccineManagement.service.VaccineService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public Vaccine findById(Integer id) {
        return null;
    }

    @Override
    public void saveVaccine(Vaccine vaccine) {

    }

    @Override
    public Page<Vaccine> findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(String name, String vaccineTypeName, String origin, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Vaccine> findAllByQuantityIsExits(String name, String vaccineType_name, String origin, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Vaccine> findAllByQuantityIsNotExits(String name, String vaccineType_name, String origin, Pageable pageable) {
        return null;
    }

    @Override
    public VaccineDTO getVaccineById(Integer id) {
        return null;
    }

    @Override
    public List<VaccineDTO> getAllVaccineDTO(int index) {
        return vaccineRepository.getAllVaccineDTO(index);
    }

    @Override
    public List<VaccineDTO> getAllVaccineDTONotPagination() {
        return vaccineRepository.getAllVaccineDTONotPagination();
    }

    @Override
    public List<VaccineDTO> search(String name, String vaccineType, String origin) {
        return vaccineRepository.search(name, vaccineType, origin);
    }

    @Override
    public void createVaccine(CreateVaccineDTO createVaccineDTO) {
        String id = "";
        int length = 4;
        boolean useLetters = true;
        boolean useNumbers = true;
        boolean isExisted = true;
        while (isExisted) {
            id = RandomStringUtils.random(length, useLetters, useNumbers).toUpperCase();
            long count = vaccineRepository.countAllByVaccineId(id);
            if (count == 0) isExisted = false;
        }
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineId(id);
        vaccine.setName(createVaccineDTO.getNameVaccine());
        vaccine.setLicenseCode(createVaccineDTO.getLicenseCode());
        vaccine.setOrigin(createVaccineDTO.getOrigin());
        vaccine.setAge(createVaccineDTO.getAge());
        vaccine.setMaintenance(createVaccineDTO.getMaintenance());
        vaccine.setImage(createVaccineDTO.getImgVaccine());
        vaccine.setDosage(createVaccineDTO.getDosage());
        vaccine.setExpired(createVaccineDTO.getExpired());
        vaccine.setQuantity(createVaccineDTO.getQuantity());
        vaccine.setDeleteFlag(false);
        vaccine.setTimes(createVaccineDTO.getTimes());
        vaccine.setDuration(createVaccineDTO.getDuration());
        vaccine.setVaccineType(createVaccineDTO.getTypeVaccine());

        vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine searchName(String name) {
        return vaccineRepository.findByName(name);
    }

    @Override
    public List<Vaccine> getAllVaccine() {
        return null;
    }

    @Override
    public Vaccine getVaccineByIdNameQuery(Integer id) {
        return null;
    }

    @Override
    public Page<Vaccine> getAllVaccineByDuration(Pageable pageable) {
        return null;
    }
}
