package com.do_an.clinic.controller;

import com.do_an.clinic.dtos.UserLoginDTO;
import com.do_an.clinic.dtos.UserRegisterDTO;
import com.do_an.clinic.models.User;
import com.do_an.clinic.response.LoginResponse;
import com.do_an.clinic.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    // Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserRegisterDTO userRegisterDTO,
            BindingResult result
    ){
        try {
            if (result.hasErrors()) {
                List<String> errorMessager = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessager);
            }
            if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getRetypePassword())) {
                return ResponseEntity.badRequest().body("Password does not match");
            }
            User user = userService.crateUser(userRegisterDTO);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Đăng nhập tài khoản
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(
                    userLoginDTO.getPhoneNumber(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getRoleId() == null ? 1 : userLoginDTO.getRoleId()
            );
            User user = userService.getUserByPhoneNumber(userLoginDTO.getPhoneNumber());

            return ResponseEntity.ok(LoginResponse.builder()
                    .userId(user.getId())
                    .token(token)
                    .roleId(user.getRole().getId())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
