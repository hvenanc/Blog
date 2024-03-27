package com.henrique.blog.controller;

import com.henrique.blog.entities.User;
import com.henrique.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/cadastrar")
    public String pageCreateUser() {
        return "/users/registro";
    }

    @PostMapping("/cadastrar")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "/users/login";
    }
}
