package com.henrique.blog.config;

import com.henrique.blog.entities.User;
import com.henrique.blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class UserInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public UserInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user_session_id") != null) {
            Long userId = Long.parseLong(session.getAttribute("user_session_id").toString());
            Optional<User> optionalUser = userService.getUserById(userId);
            if(optionalUser.isPresent()) {
                request.setAttribute("user", optionalUser.get());
            }
            else {
                return false;
            }
        }
        return true;
    }

}
