package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.LoginReq;
import com.iBME.emg_label_tool.dto.request.RefreshTokenReq;
import com.iBME.emg_label_tool.dto.response.AuthenticationResponse;
import com.iBME.emg_label_tool.entity.TokenObj;
import com.iBME.emg_label_tool.exception.RefreshTokenFailedException;
import com.iBME.emg_label_tool.exception.UnAuthorizedException;
import com.iBME.emg_label_tool.service.KeycloakService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Value("${refreshToken.expiredTime}")
    private  int EXPIRED_TIME;

    private final RedisTemplate<String, Object> redisTemplate;

    private  final KeycloakService keycloakService;

    @PostMapping("/login")
    public AuthenticationResponse Login(@RequestBody @Valid LoginReq loginReq) {
        try {
            String accessToken = keycloakService.getAccessToken(loginReq.getEmail(), loginReq.getPassword());
            String refreshToken = UUID.randomUUID().toString();
            TokenObj tokenObj = new TokenObj();
            tokenObj.setEmail(loginReq.getEmail());
            tokenObj.setPassword(loginReq.getPassword());
            tokenObj.setCreated(LocalDateTime.now());
            redisTemplate.opsForHash().put(refreshToken,refreshToken.hashCode(), tokenObj);
            redisTemplate.expire(refreshToken, 300, TimeUnit.SECONDS);
            return new AuthenticationResponse(accessToken, refreshToken);
        }
        catch (Exception e) {
            throw new UnAuthorizedException(e.getMessage());
        }
}

    @PostMapping("/refresh-token")
    public AuthenticationResponse refresh(@RequestBody @Valid RefreshTokenReq refreshTokenReq) {
        String refreshToken = refreshTokenReq.getRefresh_token();
        TokenObj tokenObj = (TokenObj) redisTemplate.opsForHash().get(refreshToken, refreshToken.hashCode());

        if (ObjectUtils.isEmpty(tokenObj)  ) {
            throw new RefreshTokenFailedException("Refresh Token isn't match or expired!");
        }
        String accessToken = keycloakService.getAccessToken(tokenObj.getEmail(), tokenObj.getPassword());
        return new AuthenticationResponse(accessToken, refreshTokenReq.getRefresh_token());


    }
}
