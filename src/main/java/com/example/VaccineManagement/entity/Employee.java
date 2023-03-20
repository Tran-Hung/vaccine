package com.example.VaccineManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String employeeId;
    private String name;
    @Column(columnDefinition = "Date")
    private String dateOfBirth;
    private String idCard;
    private String address;
    private String phone;
    private Boolean deleteFlag;
    private String positionId;
    private String accountId;
}
