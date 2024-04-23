package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.PageDTO;
import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.dto.request.*;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public MessageResponse getMe() {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = userService.getCurrentUser();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/{id}")
    public MessageResponse getById(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = userService.getUserById(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/search")
    public MessageResponse getList(
            @RequestParam(required = true) String text,
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = false) boolean ascending,
            @RequestParam(required = false) String orderBy
    ) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = userService.search(page, size, text, ascending, orderBy);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PutMapping
    public MessageResponse updateUser(@RequestBody UpdateUserReq updateUserReq) {
        MessageResponse ms = new MessageResponse();
        try {
            userService.updateUser(updateUserReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/change-password")
    public MessageResponse changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        MessageResponse ms = new MessageResponse();

        try {
            if (!userService.changePassword(changePasswordReq)) {
                ms.code = HttpStatus.NOT_FOUND.value();
                ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            }
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping("/upload-avatar")
    public MessageResponse getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        MessageResponse ms = new MessageResponse();
        try {
            userService.uploadAvatar(uploadAvatarReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

}