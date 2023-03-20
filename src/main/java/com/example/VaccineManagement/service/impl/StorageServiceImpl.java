package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.service.StorageService;
import com.example.VaccineManagement.common.SecureRandomUtil;
import com.example.VaccineManagement.entity.Storage;
import com.example.VaccineManagement.repository.StorageRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public List<Storage> findAll() {
        return null;
    }

    @Override
    public Storage findById(Integer id) {
        return null;
    }

    @Override
    public void createStorage(long quantity, String vaccineId) {
        Storage storage = new Storage();
        String id = "";
        int length = 4;
        boolean useLetters = true;
        boolean useNumbers = true;
        boolean isExisted = true;
        while (isExisted) {
            id = RandomStringUtils.random(length, useLetters, useNumbers).toUpperCase();
            long count = storageRepository.countAllByStorageId(id);
            if (count == 0) isExisted = false;
        }
        storage.setStorageId(id);
        storage.setQuantity(quantity);
        storage.setVaccineId(vaccineId);
        storageRepository.save(storage);

    }

    @Override
    public Storage getStorage(int id) {
        return null;
    }

    @Override
    public void saveStorage(Storage storage) {

    }
}
