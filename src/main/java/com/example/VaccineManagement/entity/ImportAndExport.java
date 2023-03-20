package com.example.VaccineManagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "import_and_export")
public class ImportAndExport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordNo;
    private String importAndExportId;
    private String action;
    private Long quantity;
    @Column(columnDefinition = "Date")
    private String date;
    private Long price;
    private Boolean deleteFlag;
    private String storageId;

//    @ManyToOne
//    @JoinColumn(name = "account_id",nullable = false)
//    private String accountId;
}
