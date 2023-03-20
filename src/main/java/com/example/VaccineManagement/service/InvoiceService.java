package com.example.VaccineManagement.service;

public interface InvoiceService {

    void createInvoice(String expired, long price, long quantity,String transactionDate, String provideId, String vaccineId);
}
