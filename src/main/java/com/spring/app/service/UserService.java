package com.spring.app.service;

import com.spring.app.dto.UserDto;
import com.spring.app.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    List<User> isUserExistByEmailOrPhone(String email, String phone);

    User userExist(String email,String Password);

}
