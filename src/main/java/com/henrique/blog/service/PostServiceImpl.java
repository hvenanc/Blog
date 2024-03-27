package com.henrique.blog.service;

import com.henrique.blog.entities.Post;
import com.henrique.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void updatePost(Long id, Post post) {
        Post postUpdate = getPostById(id).orElseThrow(() ->
                new InvalidParameterException("Post não encontrado!"));
        postUpdate.setTitle(post.getTitle());
        postUpdate.setContent(post.getContent());
        postRepository.save(postUpdate);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> searchPostByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }
}
