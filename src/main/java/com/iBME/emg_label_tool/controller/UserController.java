package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.PageDTO;
import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.dto.request.*;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        ResponseEntity<?> re;

        try {
            re = ResponseEntity.ok(userService.getCurrentUser());
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getById(@PathVariable long id) {
        ResponseEntity<?> re;

        try {
            re = ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }

    @GetMapping("/search")
    public ResponseEntity<?>  getList(
            @RequestParam(required = true) String text,
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = false) boolean ascending,
            @RequestParam(required = false) String orderBy
    ) {
        ResponseEntity<?> re;

        try {
            re = ResponseEntity.ok(userService.search(page, size, text, ascending, orderBy));
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }

    @PutMapping
    public ResponseEntity<?>  updateUser(@RequestBody UpdateUserReq updateUserReq) {
        ResponseEntity<?> re;
        try {
            userService.updateUser(updateUserReq);
            re = ResponseEntity.ok(null);
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return re;
    }

    @PostMapping("/change-password")
    public ResponseEntity<?>  changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        ResponseEntity<?>  re = ResponseEntity.ok(null);

        try {
            if (!userService.changePassword(changePasswordReq)) {
                re = ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Password is incorrect!");
            }
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

        return re;
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<?> getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        ResponseEntity<?>  re = ResponseEntity.ok("Upload avatar success!");
        try {
            userService.uploadAvatar(uploadAvatarReq);
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return re;
    }

}