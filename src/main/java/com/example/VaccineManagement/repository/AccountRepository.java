package com.example.VaccineManagement.repository;

import com.example.VaccineManagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUserName(String username);

    @Modifying
    @Query(value ="update account set verification_code=?1 where user_name =?2",nativeQuery = true)
    void addVerificationCode(String code,String username);

    Account findAccountByVerificationCodeAndAccountId(String code, String id);

    @Query(value = "SELECT count(1) from account where user_name = ?1", nativeQuery = true)
    Long existsByUserName(String username);

    Long countByVerificationCode(String code);

    @Modifying
    @Query(value = "insert into account(account_id,user_name,encrypt_pw,is_enabled,verification_code,email) values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void addNew(String id, String username, String password, Boolean isEnable, String verifiedCode,String email);

    @Query(value = "select account_id from account where user_name = ?1", nativeQuery = true)
    String findIdUserByUserName(String username);

    long countByAccountId(String id);
}
