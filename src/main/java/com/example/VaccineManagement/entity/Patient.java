package com.example.VaccineManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String patientId;
    private String name;
    @Column(columnDefinition = "Date")
    private String dateOfBirth;
    private String gender;
    private String guardian;
    private String phone;
    private String address;
    private String email;
    private Boolean deleteFlag;
    private String accountId;
}
