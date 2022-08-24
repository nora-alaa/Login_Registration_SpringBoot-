package com.spring.app.service;

import com.spring.app.dto.UserDto;
import com.spring.app.entity.User;
import com.spring.app.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder(4);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        //         encrypt the password using spring security
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);


        userRepository.save(user);
    }

    @Override
    public User userExist(String email, String Password) {

        User user = userRepository.findByEmail(email);
        Boolean isAuth = false;
        if(user.getPassword() !=null && !user.getPassword().isEmpty())
        {
            isAuth = passwordEncoder.matches(Password,user.getPassword());
        }

        return isAuth ? user : null;
    }

    @Override
    public List<User> isUserExistByEmailOrPhone(String email, String phone) {
        return userRepository.isUserExistByEmailOrPhone(email,phone);
    }


}

