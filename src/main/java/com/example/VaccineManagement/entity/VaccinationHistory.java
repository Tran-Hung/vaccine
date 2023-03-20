package com.example.VaccineManagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaccination_history")
public class VaccinationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String vaccinationHistoryId;
    private Boolean status;
    private Double dosage;
    private String preStatus;
    private String afterStatus;
    private Integer vaccinationTimes;
    private Boolean deleteFlag = false;
    @Column(columnDefinition = "Time")
    private String startTime;
    @Column(columnDefinition = "Time")
    private String endTime;

    private String vaccinationId;
    private String patientId;

}
