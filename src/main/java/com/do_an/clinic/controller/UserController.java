package com.do_an.clinic.controller;

import com.do_an.clinic.dtos.PasswordDTO;
import com.do_an.clinic.dtos.UserLoginDTO;
import com.do_an.clinic.dtos.UserDTO;
import com.do_an.clinic.models.User;
import com.do_an.clinic.response.LoginResponse;
import com.do_an.clinic.response.MessengerResponse;
import com.do_an.clinic.response.UserListResponse;
import com.do_an.clinic.response.UserResponse;
import com.do_an.clinic.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    // Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
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
            if (!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
                return ResponseEntity.badRequest().body("Password does not match");
            }
            User user = userService.crateUser(userDTO);
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
                    .token(token)
                    .roleId(user.getRole().getId())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //lấy user detail từ token
    @GetMapping("/details")
    public ResponseEntity<UserResponse> getUserDetails(@RequestHeader("Authorization") String token) {
        try {
            String extractedToken = token.substring(7); // Loại bỏ "Bearer " từ chuỗi token
            User user = userService.getUserDetailsFromToken(extractedToken);
            return ResponseEntity.ok(UserResponse.fromUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    // cập nhật thông tin user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long id,
                                            @RequestBody UserDTO userDTO) {
        try {
            User user = userService.updateUserById(id, userDTO);
            return ResponseEntity.ok(UserResponse.fromUser(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
   // cập nhật mật khẩu
   @PutMapping("/{id}/password")
   public ResponseEntity<?> updatePasswordById(@PathVariable("id") long id,
                                               @RequestBody PasswordDTO passwordDTO
   ){
       try {
           if(!passwordDTO.getNewPassword().equals(passwordDTO.getRetypeNewPassword())){
               return ResponseEntity.badRequest().body("Mật khẩu không khớp");
           }
           User user = userService.updatePasswordById(id, passwordDTO);
           return ResponseEntity.ok(UserResponse.fromUser(user));
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
   }

   // Lấy ra danh sách các user có vai trò là doctor và không nằm trong bảng doctor
   @GetMapping("/user-doctor")
   public ResponseEntity<?> getUserDoctors() {
       List<User> userDoctor = userService.getUserDoctor();
       List<UserResponse> userResponseDoctor = userDoctor.stream()
               .map(UserResponse::fromUser)
               .toList();
       return ResponseEntity.ok(userResponseDoctor);
   }

   // Lấy danh sách user có vai trò user
    @GetMapping("/role-user")
    public ResponseEntity<?> GetAllUsers(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,
                Sort.by("id").ascending()
        );
        Page<User> userPage = userService.getAllByRoleId(1L, pageRequest);
        Page<UserResponse> userResponsePage = userPage.map(UserResponse::fromUser);
        List<UserResponse> userResponses = userResponsePage.getContent();
        int totalPages = userResponsePage.getTotalPages();
        return ResponseEntity.ok(UserListResponse.builder()
                .userResponses(userResponses)
                .totalPages(totalPages)
                .build()
        );
    }

    // Lấy ra user theo userId
    @GetMapping("/by-user-id/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable("id") Long id){
        User user = userService.getByUserId(id);
        UserResponse userResponse = UserResponse.fromUser(user);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        MessengerResponse messengerResponse = new MessengerResponse("Xóa thành công User có id: " + id);
        return ResponseEntity.ok(messengerResponse);
    }
}
