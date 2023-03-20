package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.common.SecureRandomUtil;
import com.example.VaccineManagement.entity.Invoice;
import com.example.VaccineManagement.repository.InvoiceRepository;
import com.example.VaccineManagement.service.InvoiceService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void createInvoice(String expired, long price, long quantity, String transactionDate, String provideId, String vaccineId) {
        Invoice invoice = new Invoice();
        String id = "";
        int length = 4;
        boolean useLetters = true;
        boolean useNumbers = true;
        boolean isExisted = true;
        while (isExisted) {
            id = RandomStringUtils.random(length, useLetters, useNumbers).toUpperCase();
            long count = invoiceRepository.countAllByInvoiceId(id);
            if (count == 0) isExisted = false;
        }
        invoice.setInvoiceId(id);
        invoice.setExpired(expired);
        invoice.setPrice(price);
        invoice.setQuantity(quantity);
        invoice.setTransactionDate(transactionDate);
        invoice.setInvoiceId(provideId);
        invoice.setVaccineId(vaccineId);
        invoiceRepository.save(invoice);

    }
}
