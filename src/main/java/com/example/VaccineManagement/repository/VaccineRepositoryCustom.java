package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.bean.VaccineOrmBean;

import java.util.List;

public interface VaccineRepositoryCustom {

    List<VaccineOrmBean> getListVaccine(int index);

}
