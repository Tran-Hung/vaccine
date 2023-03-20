package com.example.VaccineManagement.controller;

import com.example.VaccineManagement.service.*;
import com.example.VaccineManagement.dto.CreateVaccineDTO;
import com.example.VaccineManagement.dto.VaccineDTO;
import com.example.VaccineManagement.entity.Provider;
import com.example.VaccineManagement.entity.Vaccine;
import com.example.VaccineManagement.entity.VaccineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class VaccineController {
    @Autowired
    protected ProviderService providerService;

    @Autowired
    protected VaccineTypeService vaccineTypeService;

    @Autowired
    protected VaccineService vaccineService;

    @Autowired
    protected StorageService storageService;

    @Autowired
    protected InvoiceService invoiceService;

    @GetMapping("/vaccines")
    public ResponseEntity<List<VaccineDTO>> getAllVaccine(@RequestParam int index) {
        List<VaccineDTO> vaccines = vaccineService.getAllVaccineDTO(index);
        if (vaccines.isEmpty()) {
            return new ResponseEntity<List<VaccineDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VaccineDTO>>(vaccines, HttpStatus.OK);
    }

    @GetMapping("/vaccines-not-pagination")
    public ResponseEntity<List<VaccineDTO>> getAllVaccineNotPagination() {
        List<VaccineDTO> vaccines = vaccineService.getAllVaccineDTONotPagination();
        if (vaccines.isEmpty()) {
            return new ResponseEntity<List<VaccineDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VaccineDTO>>(vaccines, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VaccineDTO>> search(@RequestParam(defaultValue = "") String nameVaccine,
                                                   @RequestParam(defaultValue = "") String typeVaccine,
                                                   @RequestParam(defaultValue = "") String originVaccine,
                                                   @RequestParam(defaultValue = "") String statusVaccine) {

        List<VaccineDTO> vaccines = vaccineService.search(nameVaccine, typeVaccine, originVaccine);
        List<VaccineDTO> vaccineDTOList = new ArrayList<>();

        if (statusVaccine.equals("REMAIN")) {
            for (VaccineDTO vaccineDTO : vaccines) {
                if (vaccineDTO.getQuantity() > 0) {
                    vaccineDTOList.add(vaccineDTO);
                }
            }
        } else {
            for (VaccineDTO vaccineDTO : vaccines) {
                if (vaccineDTO.getQuantity() == 0) {
                    vaccineDTOList.add(vaccineDTO);
                }
            }
        }
        if (vaccines.isEmpty()) {
            return new ResponseEntity<List<VaccineDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VaccineDTO>>(vaccineDTOList, HttpStatus.OK);
    }

    @PostMapping("/vaccine-create")
    public ResponseEntity<Void> createVaccine(@RequestBody CreateVaccineDTO createVaccineDTO) {

        // Check provider
        Provider provider = providerService.searchNameProvider(createVaccineDTO.getProvider());

        if (provider == null) {
            providerService.createProvider(createVaccineDTO.getProvider());
            provider = providerService.searchNameProvider(createVaccineDTO.getProvider());
        }
        createVaccineDTO.setProvider(provider.getProviderId() + "");


        // Check vaccine type
        VaccineType vaccineType = vaccineTypeService.findVaccineType(createVaccineDTO.getTypeVaccine());

        if (vaccineType == null) {
            vaccineTypeService.createVaccineType(createVaccineDTO.getTypeVaccine());
            vaccineType = vaccineTypeService.findVaccineType(createVaccineDTO.getTypeVaccine());
        }
        createVaccineDTO.setTypeVaccine(vaccineType.getVaccineTypeId() + "");

        // Create vaccine
        vaccineService.createVaccine(createVaccineDTO);

        Vaccine vaccine = vaccineService.searchName(createVaccineDTO.getNameVaccine());

        storageService.createStorage(createVaccineDTO.getQuantity(), vaccine.getVaccineId());
        invoiceService.createInvoice(createVaccineDTO.getExpired(), createVaccineDTO.getUnitPrice(),
                createVaccineDTO.getQuantity(), createVaccineDTO.getDayReceive(),
                provider.getProviderId(), vaccine.getVaccineId());
        HttpHeaders httpHeaders = new HttpHeaders();

        return new ResponseEntity<Void>(httpHeaders, HttpStatus.OK);
    }
}
