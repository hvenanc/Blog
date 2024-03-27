package com.henrique.blog.controller;

import com.henrique.blog.service.PostService;
import com.henrique.blog.service.PostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "/posts/home";
    }
}
