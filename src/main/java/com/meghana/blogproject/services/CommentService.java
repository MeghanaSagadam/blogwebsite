package com.meghana.blogproject.services;

import com.meghana.blogproject.payload.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    CommentDTO createComment(Long postId,CommentDTO commentdto);
    List<CommentDTO> getCommentByPostId(Long postId);
    CommentDTO getCommentById(Long postId,Long commentId);
    CommentDTO updateCommentById(Long postId,Long commentId,CommentDTO commentdto);
    void deleteCommentById(Long postId,Long commentId);
}
