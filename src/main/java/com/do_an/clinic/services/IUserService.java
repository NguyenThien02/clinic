package com.do_an.clinic.services;

import com.do_an.clinic.dtos.UserRegisterDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.User;

public interface IUserService {
    User crateUser(UserRegisterDTO userRegisterDTO) throws Exception;

    String login(String phoneNumber, String password, Long roleId) throws Exception;

    User getUserByPhoneNumber(String phoneNumber) throws DataNotFoundException;

    User getUserDetailsFromToken(String token) throws Exception;
}
