package com.example.VaccineManagement.controller;

import com.example.VaccineManagement.bean.AccountDetails;
import com.example.VaccineManagement.entity.Account;
import com.example.VaccineManagement.entity.Patient;
import com.example.VaccineManagement.jwt.JwtUtility;
import com.example.VaccineManagement.payload.request.LoginRequest;
import com.example.VaccineManagement.payload.request.ResetPassRequest;
import com.example.VaccineManagement.payload.request.SignupRequest;
import com.example.VaccineManagement.payload.response.JwtResponse;
import com.example.VaccineManagement.payload.response.MessageResponse;
import com.example.VaccineManagement.service.AccountService;
import com.example.VaccineManagement.service.PatientService;
import com.example.VaccineManagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public")
public class SecurityController {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
            AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Account account = accountService.findAccountByUserName(loginRequest.getUsername());
            Patient patient = patientService.findByAccountId(account.getAccountId(), false);

//        if (patient != null) {
//            patient.setAccount(null);
//        }

            return ResponseEntity.ok(
                    new JwtResponse(
                            jwt,
                            userDetails.getId(),
                            userDetails.getUsername(),
                            roles,
                            patient)
            );
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Tài khoản chưa tồn tại hoặc chưa được kích hoạt"));
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {

        if (accountService.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Name đã được đăng ký!!!"));
        }

        // Create new user's account
        Account account = new Account(signUpRequest.getName(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        //Add new user's account to database
        accountService.addNew(account.getUserName(), account.getEncryptPw(), signUpRequest.getEmail());
        //Find ID user's account newest after add to database
        String idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        //Set default role is "ROLE_USER"
        roleService.setDefaultRole(idAccountAfterCreated, "USER");
        //Add new patient
        patientService.addPatient(
                signUpRequest.getName(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getGender(),
                signUpRequest.getGuardian(),
                signUpRequest.getPhone(),
                signUpRequest.getAddress(),
                signUpRequest.getEmail(), idAccountAfterCreated, false);
        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> VerifyEmail(@RequestParam(defaultValue = "") String code,
                                         @RequestParam(defaultValue = "") String id) {
        Boolean isVerified = accountService.findAccountByVerificationCode(code, id);
        if (isVerified) {
            return ResponseEntity.ok(new MessageResponse("Account have activated"));
        } else {
            return ResponseEntity.ok(new MessageResponse("error"));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> reset(@RequestBody LoginRequest loginRequest) throws MessagingException, UnsupportedEncodingException {

        if (accountService.existsByName(loginRequest.getUsername())) {
            accountService.addVerificationCode(loginRequest.getUsername());
            return ResponseEntity.ok(new MessageResponse("Sent email "));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Tài khoản không đúng"));
    }

    @GetMapping("/verify-reset-password")
    public ResponseEntity<?> VerifyPassword(@RequestParam(defaultValue = "") String code,
                                            @RequestParam(defaultValue = "") String userName) {
        Boolean isVerified = accountService.findAccountByVerificationCodeToResetPassword(code, userName);
        if (isVerified) {
            return ResponseEntity.ok(new MessageResponse("Password has changed"));
        } else {
            return ResponseEntity.ok(new MessageResponse("error"));
        }
    }

    @PostMapping("/do-reset-password")
    public ResponseEntity<?> doResetPassword(@RequestBody ResetPassRequest resetPassRequest) {
        accountService.saveNewPassword(encoder.encode(resetPassRequest.getPassword()), resetPassRequest.getCode());
        return ResponseEntity.ok(new MessageResponse("success"));
    }

}
