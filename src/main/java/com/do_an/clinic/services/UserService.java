package com.do_an.clinic.services;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User crateUser(UserRegisterDTO userRegisterDTO) throws Exception {
        String phoneNumber = userRegisterDTO.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        Role role = roleRepository.findById(userRegisterDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        if(role.getName().equals(Role.ADMIN)){
            throw new PermissionDenyException("You cannot register an admin account");
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
}
