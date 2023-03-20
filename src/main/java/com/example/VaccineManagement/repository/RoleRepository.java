package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query(value = "insert into account_role(account_role_id, account_id,role_id) values (?1,?2,?3)", nativeQuery = true)
    void setDefaultRole(String id, String accountId, String roleId);

    long countByRoleId(String id);

}
