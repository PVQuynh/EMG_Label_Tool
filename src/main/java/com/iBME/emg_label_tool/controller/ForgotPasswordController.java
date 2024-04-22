package com.iBME.emg_label_tool.controller;


import com.iBME.emg_label_tool.dto.request.ConfirmOTP;
import com.iBME.emg_label_tool.dto.request.ForgotPasswordReq;
import com.iBME.emg_label_tool.dto.request.RegisterReq;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.entity.User;
import com.iBME.emg_label_tool.service.EmailService;
import com.iBME.emg_label_tool.service.KeycloakService;
import com.iBME.emg_label_tool.service.OTPService;
import com.iBME.emg_label_tool.service.UserService;
import com.iBME.emg_label_tool.utils.RandomString;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final KeycloakService keycloakService;

    private final UserService userService;

    private final OTPService otpService;

    private final EmailService emailService;

    private final RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/generate-otp")
    public MessageResponse generateOTP(@RequestBody @Valid ForgotPasswordReq forgotPasswordReq) {
        MessageResponse ms = new MessageResponse();
        ms.message = "Sent";
        String email = forgotPasswordReq.getEmail();

        try {
            if (userService.findByEmail(email).isPresent()) {
                int otp = otpService.generateOTP(email);

                //Generate The Template to send OTP
                String message = "Your OTP verified number is: " + otp;
                emailService.sendOtpMessage(forgotPasswordReq.getEmail(), "OTP verified code", message);
            } else {
                ms.code = HttpStatus.NOT_FOUND.value();
                ms.message = "Email is not used, please enter correct email!";
            }

        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_ACCEPTABLE.value();
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PostMapping("/validate-otp-and-password-retrieval")
    public MessageResponse validateOtp(@RequestBody @Valid ConfirmOTP confirmOTP) {
        final String SUCCESS = "OTP is correct, please check your email to retrieve your password!!";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
        MessageResponse ms = new MessageResponse();
        ms.message = SUCCESS;

        // lay email va opt tu request
        String email = confirmOTP.getEmail();
        int otpnum = confirmOTP.getOtpNum();

        //Validate the Otp
        if (otpnum >= 0) {
            int serverOtp = otpService.getOtp(email);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(email);
                    //Save Account
                    try {
                        User user = userService.randomlyGeneratePassword(email);

                        // Send new password to email
                        String message = "Your new password is: " + user.getPassword();
                        emailService.sendOtpMessage(email, "New Password For Your EMG Label Tool", message);
                    } catch (Exception e) {
                        ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
                        ms.message = e.getMessage();
                    }
                } else {
                    ms.code = HttpStatus.UNAUTHORIZED.value();
                    ms.message = FAIL;
                }
            } else {
                ms.code = HttpStatus.UNAUTHORIZED.value();
                ms.message = FAIL;
            }
        } else {
            ms.code = HttpStatus.UNAUTHORIZED.value();
            ms.message = FAIL;
        }
        return ms;
    }

}
