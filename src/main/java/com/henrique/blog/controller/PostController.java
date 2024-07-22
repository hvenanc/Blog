package com.henrique.blog.controller;

import com.henrique.blog.entities.Comment;
import com.henrique.blog.entities.Post;
import com.henrique.blog.entities.User;
import com.henrique.blog.service.PostService;
import com.henrique.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "/posts/home";
    }

    @GetMapping("/novo")
    public String newPostPage() {
        return "/posts/create-post";
    }

    @PostMapping("/criar")
    public String createPost(Post post, HttpSession session) {
        post.setCreateAt(LocalDateTime.now());

        User userSession = userService.getUserById(Long.parseLong(session.getAttribute("user_session_id").toString())).get();
        post.setUser(userSession);
        postService.createPost(post);
        return "redirect:/post/home";
    }

    @GetMapping("/detalharPost/{id}")
    public String postPage(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("Id Invalido"));
        List<Comment> comments = post.getCommentListPosts();

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "/posts/post-page";
    }

    @GetMapping("/meusPosts")
    public String myPosts(Model model, HttpSession session) {

        Long userId = Long.parseLong(session.getAttribute("user_session_id").toString());
        List<Post> postsByUser = postService.getPostsByUserId(userId);

        model.addAttribute("posts", postsByUser);

        return "/posts/my-posts";
    }
}
