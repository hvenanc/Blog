package com.henrique.blog.service;

import com.henrique.blog.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    List<Post> getPostsByUserId(Long userId);
    void createPost(Post post);
    void updatePost(Long id, Post post);
    void deletePostById(Long id);
    List<Post> searchPostByTitle(String title);
}
