package com.henrique.blog.controller;

import com.henrique.blog.entities.User;
import com.henrique.blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/cadastrar")
    public String pageCreateUser() {
        return "/users/registro";
    }

    @PostMapping("/cadastrar")
    public String createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "/users/login";
    }

    @GetMapping("/entrar")
    public String accessPage(HttpSession session) {
        Optional<User> optionalUser = userService.getUserById
                (Long.parseLong(session.getAttribute("user_session_id").toString()));

        if(optionalUser.isPresent()) {
            session.setAttribute("user_session_id", optionalUser.get().getId());
            return "redirect:/post/home";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/sair")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
