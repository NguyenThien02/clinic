package com.do_an.clinic.services;

import com.do_an.clinic.dtos.PasswordDTO;
import com.do_an.clinic.dtos.UserDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.User;

import java.util.List;

public interface IUserService {
    User crateUser(UserDTO userDTO) throws Exception;

    String login(String phoneNumber, String password, Long roleId) throws Exception;

    User getUserByPhoneNumber(String phoneNumber) throws DataNotFoundException;

    User getUserDetailsFromToken(String token) throws Exception;

    User updateUserById(long id, UserDTO userDTO) throws DataNotFoundException;

    User updatePasswordById(long id, PasswordDTO passWordDTO) throws DataNotFoundException;
    List<User> getUserDoctor() ;

}

