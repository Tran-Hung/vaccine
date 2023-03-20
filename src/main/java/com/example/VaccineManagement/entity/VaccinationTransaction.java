package com.example.VaccineManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccination_transaction")
public class VaccinationTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String vaccinationTransactionId;

    private String vaccinationHistoryId;

    private Double price;
    private Long quantity;

}
