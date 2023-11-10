package com.webapp.BRS.service;

import com.webapp.BRS.dto.UserDto;
import com.webapp.BRS.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

}