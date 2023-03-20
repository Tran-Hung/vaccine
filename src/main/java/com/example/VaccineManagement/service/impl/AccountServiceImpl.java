package com.example.VaccineManagement.service.impl;

import com.example.VaccineManagement.common.MyConstants;
import com.example.VaccineManagement.entity.Account;
import com.example.VaccineManagement.entity.Patient;
import com.example.VaccineManagement.entity.Vaccination;
import com.example.VaccineManagement.entity.VaccinationHistory;
import com.example.VaccineManagement.repository.AccountRepository;
import com.example.VaccineManagement.repository.PatientRepository;
import com.example.VaccineManagement.service.AccountService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JavaMailSender javaMailSender;

//    @Autowired
//    private VaccinationRepository vaccinationRepository;
//    @Autowired
//    private PatientRepository patientRepository;
//    @Autowired
//    private VaccinationHistoryRepository vaccinationHistoryRepository;

    @Override
    public Account findAccountByUserName(String username) {
        return accountRepository.findAccountByUserName(username);
    }

    @Override
    public String findIdUserByUserName(String username) {
        return accountRepository.findIdUserByUserName(username);
    }

    @Override
    public Boolean existsByName(String username) {
        return accountRepository.existsByUserName(username) > 0;
    }

    @Override
    @Transactional
    public void addNew(String username, String password, String email) throws MessagingException, UnsupportedEncodingException {
        String randomCode = RandomString.make(4).toUpperCase();
        String id = "";
        boolean isExisted = true;
        while (isExisted) {
            id = RandomString.make(4).toUpperCase();
            long count = accountRepository.countByAccountId(id);
            if (count == 0) isExisted = false;
        }
        accountRepository.addNew(id, username, password, false, randomCode, email);
        sendVerificationEmail(id, username, randomCode, email);
    }

    @Override
    public Boolean findAccountByVerificationCode(String code, String id) {
        Account account = accountRepository.findAccountByVerificationCodeAndAccountId(code, id);
        if (account != null) {
            account.setEnabled(true);
            accountRepository.save(account);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean findAccountByVerificationCodeToResetPassword(String code, String userName) {
        Account account = accountRepository.findAccountByVerificationCodeAndAccountId(code, userName);
        return account != null;
    }

    @Override
    public void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException {
        String code = RandomString.make(4);
        accountRepository.addVerificationCode(code, username);
        Account account = accountRepository.findAccountByUserName(username);
        this.sendVerificationEmailForResetPassWord(account.getUserName(), code, account.getEmail());
    }

    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    @Override
    public void addNew(String username, String password) {

    }

    @Override
    public void saveNewPassword(String password, String code) {

    }

//    @Override
//    public void sendInfoEmail(PeriodicalVaccinationTempRegisterDTO register, VaccinationHistory vaccinationHistory) throws MessagingException, UnsupportedEncodingException {
//        Patient patient = this.patientRepository.getOne(register.getPatientId());
//        Vaccination vaccination = this.vaccinationRepository.getOne(register.getVaccinationId());
//        StringBuilder randomCode = new StringBuilder();
//        randomCode.append(register.getVaccinationId()).append("|").append(register.getPatientId());
//        String subject = "Thông tin đăng ký tiêm chủng của bạn";
//        String mailContent = "";
//        String cancelRegisterUrl = "http://localhost:4200/cancel-register?code=" + randomCode;
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
//        helper.setTo(patient.getEmail());
//        helper.setFrom("vanlinh12b5@gmail.com","TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG");
//        helper.setSubject(subject);
//        mailContent = "<span style=\"font-weight: bold\">Xin chào<span> "+patient.getGuardian()+",</span></span>\n" +
//                "<br><br>\n" +
//                "<span style=\"font-weight: bold\"> "+patient.getName()+"</span> <span>vừa được đăng ký tiêm chủng định kỳ với thông tin sau:</span>\n" +
//                "<br><br>\n" +
//                "<span style=\"font-weight: bold\"> Ngày tiêm chủng: </span><span>"+vaccination.getDate()+"</span>\n" +
//                "<br><br>\n" +
//                "<span style=\"font-weight: bold\"> Giờ tiêm chủng: </span><span>"+vaccinationHistory.getStartTime()+"  - "+vaccinationHistory.getEndTime()+"</span>\n" +
//                "<br><br>\n" +
//                "<span style=\"font-weight: bold\"> Địa điểm: </span><span> Trung Tâm y tế dự phòng - 103 Hùng Vương, Hải Châu, Đà Nẵng </span>\n" +
//                "<br><br>\n" +
//                "<span style=\"font-weight: bold\"> Tên Vắc xin: </span><span>"+vaccination.getVaccine().getName()+" </span>\n" +
//                "<br><br>\n" +
//                "<span style=\"font-weight: bold\"> Xuất xứ: </span><span>"+vaccination.getVaccine().getOrigin()+" </span>\n" +
//                "<br><br>\n" +
//                "<p style=\"font-style: italic; color: red\">Trong trường hợp bạn không thể tham gia vì lý do nào đó, bạn có thể hủy đăng ký bằng link bên dưới:</p>\n" +
//                "<h3><a href='" + cancelRegisterUrl + "'>Link hủy đăng ký!</a></h3>" +
//                "<p>TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG KÍNH BÁO</p>";
//        helper.setText(mailContent, true);
//        javaMailSender.send(message);
//    }

    public void sendVerificationEmail(String id, String userName, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";
        String confirmUrl = "http://localhost:8080/api/public/verify?code=" + randomCode + "&id=" + id;


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(email);
        helper.setFrom(MyConstants.MY_EMAIL, "TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG");
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>" + "<p> Nhấn vào link sau để xác thực email của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
                "<p>TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    public void sendVerificationEmailForResetPassWord(String userName, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";
        String confirmUrl = "http://localhost:8080/verify-reset-password?code=" + randomCode+ "&userName=" + userName;


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom(MyConstants.MY_EMAIL, "TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG");
        helper.setTo(email);
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>" + "<p> Nhấn vào link sau để xác thực email của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
                "<p>TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

}
