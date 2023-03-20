package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findByName(String name);

    long countAllByProviderId(String provideId);

}
