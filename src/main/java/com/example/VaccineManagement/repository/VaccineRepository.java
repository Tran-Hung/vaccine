package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.dto.VaccineDTO;
import com.example.VaccineManagement.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Vaccine findByName(String name);

    long countAllByVaccineId(String vaccineId);

    @Query(value = "select vaccine.vaccine_id as id,vaccine.name as name, vaccine_type.name as vaccineType,invoice.transaction_date as dayReceive, " +
            "vaccine.license_code as licenseCode, vaccine.origin as origin, vaccine.dosage as dosage, " +
            "vaccine.expired as expired, vaccine.maintenance as maintenance, vaccine.age as age, vaccine.duration as duration, vaccine.times as times, storage.quantity as quantity " +
            "from vaccine " +
            "left join vaccine_type on vaccine.vaccine_type = vaccine_type.vaccine_type_id " +
            "left join storage on storage.vaccine_id = vaccine.vaccine_id " +
            "left join invoice on invoice.vaccine_id = vaccine.vaccine_id " +
            "where vaccine.delete_flag = 0 " +
            "group by vaccine.vaccine_id limit ?1,5;", nativeQuery = true)
    List<VaccineDTO> getAllVaccineDTO(int index);

    @Query(value = "SELECT vaccine.vaccine_id as id,vaccine.name as name, vaccine_type.name as vaccineType,invoice.transaction_date as dayReceive, " +
            "vaccine.license_code as licenseCode, vaccine.origin as origin, vaccine.dosage as dosage," +
            "vaccine.expired as expired, vaccine.maintenance as maintenance, vaccine.age as age, storage.quantity as quantity" +
            " FROM vaccine " +
            "left join vaccine_type on vaccine.vaccine_type = vaccine_type.vaccine_type_id " +
            "left join storage on storage.vaccine_id = vaccine.vaccine_id " +
            "left join invoice on invoice.vaccine_id = vaccine.vaccine_id " +
            "WHERE vaccine.delete_flag = 0 " +
            "group by vaccine.vaccine_id", nativeQuery = true)
    List<VaccineDTO> getAllVaccineDTONotPagination();

    @Query(value = "SELECT vaccine.vaccine_id as id,vaccine.name as name, vaccine_type.name as vaccineType,invoice.transaction_date as dayReceive, " +
            "vaccine.license_code as licenseCode, vaccine.origin as origin, vaccine.dosage as dosage," +
            "vaccine.expired as expired, vaccine.maintenance as maintenance, vaccine.age as age, storage.quantity as quantity " +
            "FROM vaccine left join vaccine_type on vaccine.vaccine_type = vaccine_type.vaccine_type_id " +
            "left join storage on storage.vaccine_id = vaccine.vaccine_id " +
            "left join invoice on invoice.vaccine_id = vaccine.vaccine_id " +
            "where vaccine.name like %?1% and vaccine_type.name like %?2% and vaccine.origin like %?3% " +
            "and vaccine.delete_flag = 0 " +
            "group by vaccine.vaccine_id", nativeQuery = true)
    List<VaccineDTO> search(String name, String vaccineType, String origin);
}
