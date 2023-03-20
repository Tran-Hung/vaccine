package com.example.VaccineManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String invoiceId;
    private String transactionDate;
    private Long price;
    @Column(columnDefinition = "Date")
    private String expired;
    private Boolean deleteFlag;
    private Long quantity;
    private String providerId;
//    @ManyToOne
//    @JoinColumn(name = "vaccine_id",nullable = false)
    private String vaccineId;
}
