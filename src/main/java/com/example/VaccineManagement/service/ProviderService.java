package com.example.VaccineManagement.service;

import com.example.VaccineManagement.entity.Provider;

public interface ProviderService {

    /**
     * TinVT
     * create Provider
     * @return
     */
    void createProvider(String name);

    /**
     * TinVT
     * find by name Provider
     * @return
     */
    Provider searchNameProvider(String name);
}
