package com.spring.app.repository;

import com.spring.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phone = ?1")
    public User findByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.email = ?1 or u.phone = ?2")
    public List<User> isUserExistByEmailOrPhone(String email, String phone);

    @Query("SELECT u FROM User u WHERE u.email = ?1 and u.password = ?2")
    public User isUserExsit(String email, String password);

}
