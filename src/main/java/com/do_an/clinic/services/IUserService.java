package com.do_an.clinic.services;

import com.do_an.clinic.dtos.UserRegisterDTO;
import com.do_an.clinic.models.User;

public interface IUserService {
    User crateUser(UserRegisterDTO userRegisterDTO) throws Exception;
}
