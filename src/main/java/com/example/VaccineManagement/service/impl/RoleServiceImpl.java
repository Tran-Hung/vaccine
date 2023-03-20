package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.entity.Role;
import com.example.VaccineManagement.repository.RoleRepository;
import com.example.VaccineManagement.service.RoleService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void setDefaultRole(String accountId, String roleId) {
        String id = "";
        boolean isExisted = true;
        while (isExisted) {
            id = RandomString.make(4).toUpperCase();
            long count = roleRepository.countByRoleId(id);
            if (count == 0) isExisted = false;
        }
        roleRepository.setDefaultRole(id, accountId, roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
