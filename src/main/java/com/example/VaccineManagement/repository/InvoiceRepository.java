package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    long countAllByInvoiceId(String id);
}
