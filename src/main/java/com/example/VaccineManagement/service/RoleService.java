package com.example.VaccineManagement.service;

import com.example.VaccineManagement.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole();

    void setDefaultRole(String accountId, String roleId);

    List<Role> getAllRoles();
}
