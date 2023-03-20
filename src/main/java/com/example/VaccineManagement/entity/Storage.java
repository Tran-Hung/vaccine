package com.example.VaccineManagement.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String storageId;
    private Long quantity;
//    @ManyToOne
//    @JoinColumn(name = "vaccine_id",nullable = false)
    private String vaccineId;
}
