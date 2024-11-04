package com.do_an.clinic.services;

import com.do_an.clinic.components.JwtTokenUtils;
import com.do_an.clinic.dtos.UserRegisterDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.exceptions.PermissionDenyException;
import com.do_an.clinic.models.Role;
import com.do_an.clinic.models.User;
import com.do_an.clinic.repository.RoleRepository;
import com.do_an.clinic.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    //Đăng ký tài khoản
    @Override
    @Transactional
    public User crateUser(UserRegisterDTO userRegisterDTO) throws Exception {
        String phoneNumber = userRegisterDTO.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataIntegrityViolationException("Số điện thoại đã tồn tại");
        }
        Role role = roleRepository.findById(userRegisterDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy Role"));
        if(role.getName().equals(Role.ADMIN)){
            throw new PermissionDenyException("Bạn không thể đăng ký tài khoản Admin");
        }
        User newUser = User.builder()
                .fullName(userRegisterDTO.getFullName())
                .phoneNumber((userRegisterDTO.getPhoneNumber()))
                .password(userRegisterDTO.getPassword())
                .birthday(userRegisterDTO.getBirthday())
                .address(userRegisterDTO.getAddress())
                .build();
        newUser.setRole(role);
        String passwrod = userRegisterDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(passwrod);
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    // Đăng nhập tài khoản
    @Override
    public String login(String phoneNumber, String password, Long roleId) throws Exception {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Số điện thoại không hợp lệ");
        }
        User existingUser = optionalUser.get();
        if(!passwordEncoder.matches(password,existingUser.getPassword())){
            throw new BadCredentialsException("Số điện thoại hoặc mật khẩu không đúng");
        }
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if(optionalRole.isEmpty() || !roleId.equals(existingUser.getRole().getId())) {
            throw new DataNotFoundException("Bạn không có quyền này");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                phoneNumber,password,existingUser.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtils.generateToken(existingUser);
    }

    // TÌm User theo số điện thoại
    @Override
    public User getUserByPhoneNumber(String phoneNumber) throws DataNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người dùng có số điện thoại: " + phoneNumber));
    }

    // Lấy user detail từ token
    @Override
    public User getUserDetailsFromToken(String token) throws Exception {
        if(jwtTokenUtils.isTokenExpired(token)){
            throw new Exception("Token đã hết hạn");
        }
        String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return user.get();
    }
}
