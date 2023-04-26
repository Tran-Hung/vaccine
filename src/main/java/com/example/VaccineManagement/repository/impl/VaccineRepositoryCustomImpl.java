package com.example.VaccineManagement.repository.impl;

import com.example.VaccineManagement.bean.VaccineOrmBean;
import com.example.VaccineManagement.repository.VaccineRepositoryCustom;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VaccineRepositoryCustomImpl implements VaccineRepositoryCustom {

    @PersistenceContext(unitName = "ols-pu")
    private EntityManager em;

    @Override
    public List<VaccineOrmBean> getListVaccine(int index) {
        List<VaccineOrmBean> vaccineOrmBeanList = new ArrayList<>();
        try {
            String sql = em.createNamedQuery("getListVaccine").unwrap(org.hibernate.query.Query.class)
                    .getQueryString();
            Query query = em.createNativeQuery(sql, "VaccineMapping");
            query.setParameter("index", index);
            vaccineOrmBeanList = query.getResultList();
            return vaccineOrmBeanList;
        } catch (Exception e) {
            log.error("DataError", e);
        }
        return vaccineOrmBeanList;
    }
}
