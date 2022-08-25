package com.spring.app.controller;

import com.spring.app.dto.UserDto;
import com.spring.app.entity.User;
import com.spring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;


//@RestController
//@RequestMapping("api")
@Controller
public class MainController {

//    // http://localhost:8080/api/age
//    @GetMapping("/age")
//    public String getAge(){
//        return "21";
//    }

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }


    @GetMapping("/welcomepage")
    public String welcomePage(){
//        model.addAttribute("name",name);
        return "welcomepage";
    }




    // handler method to handle login request
    @GetMapping("/login")
    public String login(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "login";
    }


    @PostMapping("/login/check")
    public String loginCheck(@Valid @ModelAttribute("user") UserDto userDto,
                        BindingResult result,
                        Model model) {


        if (result.hasErrors() && result.getFieldValue("email").toString().isEmpty() && result.getFieldValue("email").toString().isEmpty()) {
            model.addAttribute("user", userDto);
            return "/login";
        }

        User existingUser = userService.userExist(userDto.getEmail(), userDto.getPassword());

        if (existingUser != null && existingUser.getEmail() != result.getFieldValue("email").toString()) {
            model.addAttribute("name",  existingUser.getUserName());

            return "/welcomepage";
        }

        else {

            result.rejectValue("email", null,
                    "wrong email or password");
            model.addAttribute("user", userDto);
            return "/login";
        }



    }





    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }


    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        List<User> existingUser = userService.isUserExistByEmailOrPhone(userDto.getEmail(),userDto.getPhone());

        if(existingUser != null && !existingUser.isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email or phone");
            model.addAttribute("user", userDto);
            return "/register";
        }


            userService.saveUser(userDto);

        return "redirect:/register?success";
    }




}
