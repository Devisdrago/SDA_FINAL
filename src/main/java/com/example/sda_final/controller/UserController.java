package com.example.sda_final.controller;

import com.example.sda_final.entity.UserEntity;
import com.example.sda_final.repository.UserRepository;
import com.example.sda_final.serivce.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Controller
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserEntity());
        return "register_page";
    }

    @GetMapping("/")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserEntity());
        return "login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserEntity userEntity) {
        System.out.println("register request : " + userEntity);
        UserEntity registerUser = userService.registerUser(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getDateOfBirth());
        return registerUser != null ? "login" : "redirect:error_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserEntity userEntity, Model model) {
        System.out.println("login request : " + userEntity);
        UserEntity authenticateUser = userService.authenticate(userEntity.getUsername(), userEntity.getPassword());
        if (authenticateUser != null) {
            model.addAttribute("userLogin" , authenticateUser.getUsername());
            return "homePage";
        } else {
            return "error_page";
        }
    }

    @GetMapping("/home")
    public String getUserPage(){
        return "homePage";
    }

    @DeleteMapping("/deleteUser/{username}")
     public String deleteUser(@PathVariable String username ){
        userRepository.deleteUserEntityByUsername(username);
        return "/login";
     }

}
