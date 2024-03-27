package com.henrique.blog.service;

import com.henrique.blog.entities.Comment;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> getCommentById(Long id);
    void createComment(Comment comment);
    void updateComment(Long id, Comment comment);
    void deleteComment(Long id);
}
