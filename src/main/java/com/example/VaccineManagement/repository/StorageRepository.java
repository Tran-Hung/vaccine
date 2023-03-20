package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    long countAllByStorageId(String id);
}
