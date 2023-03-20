package com.example.VaccineManagement.service;

import com.example.VaccineManagement.entity.Storage;

import java.util.List;

public interface StorageService {

    List<Storage> findAll();

    Storage findById(Integer id);

    /**
     * TinVT
     * create Storage
     * @return
     */
    void createStorage(long quantity , String vaccineId);

    /**
     * PhucNB
     * @param id
     * @return
     */
    Storage getStorage(int id);
    /**
     * PhucNB
     * @param storage
     */
    void saveStorage(Storage storage);
}
