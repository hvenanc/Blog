package com.henrique.blog.service;

import com.henrique.blog.entities.Comment;
import com.henrique.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Long id, Comment comment) {
        Comment commentUpdate = getCommentById(id).orElseThrow(() ->
                new InvalidParameterException("Comentário não localizado"));
        commentUpdate.setContent(comment.getContent());
        commentRepository.save(commentUpdate);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
