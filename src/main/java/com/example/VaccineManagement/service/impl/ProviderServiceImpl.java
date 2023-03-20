package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.common.SecureRandomUtil;
import com.example.VaccineManagement.entity.Provider;
import com.example.VaccineManagement.repository.ProviderRepository;
import com.example.VaccineManagement.service.ProviderService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;
    @Override
    public void createProvider(String name) {
        String id = "";
        int length = 4;
        boolean useLetters = true;
        boolean useNumbers = true;
        boolean isExisted = true;
        while (isExisted) {
            id = RandomStringUtils.random(length, useLetters, useNumbers).toUpperCase();
            long count = providerRepository.countAllByProviderId(id);
            if (count == 0) isExisted = false;
        }
        Provider provider = new Provider();
        provider.setProviderId(id);
        provider.setName(name);
        providerRepository.save(provider);
    }

    @Override
    public Provider searchNameProvider(String name) {
        return providerRepository.findByName(name);
    }
}
