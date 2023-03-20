package com.example.VaccineManagement.service;

import com.example.VaccineManagement.entity.Account;
import com.example.VaccineManagement.entity.VaccinationHistory;


import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AccountService {

    Account findAccountByUserName(String username);

    String findIdUserByUserName(String username);

    Boolean existsByName(String username);

    void addNew(String username, String password, String email) throws MessagingException, UnsupportedEncodingException;

    Boolean findAccountByVerificationCode(String code, String id);


    Boolean findAccountByVerificationCodeToResetPassword(String code, String userName);

    void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException;

    List<Account> getAllAccount();

    void addNew(String username, String password);

    void saveNewPassword(String password,String code);

//    void sendInfoEmail(PeriodicalVaccinationTempRegisterDTO register, VaccinationHistory vaccinationHistory) throws MessagingException, UnsupportedEncodingException;
}
