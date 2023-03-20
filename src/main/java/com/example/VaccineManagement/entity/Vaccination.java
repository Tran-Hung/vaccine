package com.example.VaccineManagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccination")
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String vaccinationId;
    @Column(columnDefinition = "Time")
    private String startTime;
    @Column(columnDefinition = "Time")
    private String endTime;
    @Column(columnDefinition = "Date")
    private String date;
    private Boolean status;
    private String description;
    private Boolean deleteFlag;
    private String vaccineId;
    private String vaccinationTypeId;
    private String locationId;
}
