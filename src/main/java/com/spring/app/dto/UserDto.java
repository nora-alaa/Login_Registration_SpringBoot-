package com.spring.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;

    @NotEmpty(message = "invalid, Please insert User Name ")
    private String userName;

    @NotEmpty(message = "invalid, Please insert password ")
    private String password;

    @NotEmpty(message = "invalid, Please insert email ")
    @Email
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid Email")
    private String email;

    @NotEmpty(message = "invalid, Please insert phone ")
//    @Size(min = 11,max = 11,message = "please insert 15 digit")
    @Pattern(regexp = "^01[0125][0-9]{8}$", message = "Invalid pho ne number")
    private String phone;

    @NotEmpty(message = "invalid, Please insert first name ")
    private String firstName;


    @NotEmpty(message = "invalid, Please insert last name ")
    private String lastName;


}
