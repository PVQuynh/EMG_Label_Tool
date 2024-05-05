package com.iBME.emg_label_tool.controller;


import com.iBME.emg_label_tool.dto.request.ConfirmOTP;
import com.iBME.emg_label_tool.dto.request.RegisterReq;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.entity.User;
import com.iBME.emg_label_tool.service.EmailService;
import com.iBME.emg_label_tool.service.KeycloakService;
import com.iBME.emg_label_tool.service.OTPService;
import com.iBME.emg_label_tool.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final KeycloakService keycloakService;

    private final UserService userService;

    private final OTPService otpService;

    private final EmailService emailService;

    private final RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/generate-otp")
    public ResponseEntity<?> generateOTP(@RequestBody @Valid RegisterReq registerReq) {
        ResponseEntity<?> re = ResponseEntity
                .status(HttpStatus.OK)
                .body("Sent");
//        MessageResponse ms = new MessageResponse();
//        ms.message = "Sent";
        String email = registerReq.getEmail();

        try {
            if (userService.findByEmail(registerReq.getEmail()).isPresent()) {
//                ms.code = HttpStatus.NOT_FOUND.value();
//                ms.message = "Your email has been used!";
                re = ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Your email has been used!");
            } else {
                int otp = otpService.generateOTP(email);
                //Generate The Template to send OTP

                String message = "Your OTP verified number is: " + otp;
                redisTemplate.opsForHash().getOperations().delete(email);
                redisTemplate.opsForHash().put(email, email.hashCode(), registerReq);
                redisTemplate.expire(email, 300, TimeUnit.SECONDS);

                emailService.sendOtpMessage(registerReq.getEmail(), "OTP verified code", message);
            }

        } catch (Exception ex) {
            re = ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(ex.getMessage());
//            ms.code = HttpStatus.NOT_ACCEPTABLE.value();
//            ms.message = ex.getMessage();
        }
        return re;
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestBody @Valid ConfirmOTP confirmOTP) {
        final String SUCCESS = "Register Successfully!";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";

//        MessageResponse ms = new MessageResponse();
//        ms.message = SUCCESS;
        ResponseEntity<?> re = ResponseEntity
                .status(HttpStatus.OK)
                .body(SUCCESS);
        String email = confirmOTP.getEmail();
        RegisterReq registerReq =   (RegisterReq) redisTemplate.opsForHash().get(email,email.hashCode());
        int otpnum = confirmOTP.getOtpNum();

        //Validate the Otp
        if (otpnum >= 0) {
            int serverOtp = otpService.getOtp(email);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(email);
                    //Save Account
                    try {
                        User user = userService.create(registerReq);
                        if (ObjectUtils.isNotEmpty(user)) {
                            keycloakService.createUser(registerReq);
                        }
                        redisTemplate.opsForHash().getOperations().delete(email);
                    } catch (Exception e) {
                        re = ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(e.getMessage());
//                        ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
//                        ms.message = e.getMessage();
                    }
                } else {
                    re = ResponseEntity
                            .status(HttpStatus.UNAUTHORIZED)
                            .body(FAIL);
//                    ms.code = HttpStatus.UNAUTHORIZED.value();
//                    ms.message = FAIL;
                }
            } else {
                re = ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(FAIL);
//                ms.code = HttpStatus.UNAUTHORIZED.value();
//                ms.message = FAIL;
            }
        } else {
            re = ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(FAIL);
//            ms.code = HttpStatus.UNAUTHORIZED.value();
//            ms.message = FAIL;
        }
        return re;
    }


}
