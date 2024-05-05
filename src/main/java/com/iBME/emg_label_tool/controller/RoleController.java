package com.iBME.emg_label_tool.controller;


import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.entity.Role;
import com.iBME.emg_label_tool.service.KeycloakService;
import com.iBME.emg_label_tool.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private  final KeycloakService keycloakService;
    private  final RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<?> getRoles(){
//        MessageResponse ms = new MessageResponse();
        ResponseEntity<?> re;
        try {
//            ms.data = roleService.getAllRole();
            re = ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRole());
        } catch (Exception ex) {
//            ms.code = HttpStatus.NOT_FOUND.value();
//            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            re = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
        return re;
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role) {
//        MessageResponse ms = new MessageResponse();
        ResponseEntity<?> re = ResponseEntity.status(HttpStatus.OK).body("Create Role SuccessFully!");
        try {
            keycloakService.createRole(role);
            roleService.create(role);
        } catch (Exception ex) {
//            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
//            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return re;
    }

}
