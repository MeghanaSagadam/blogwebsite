package com.meghana.blogproject.controller;

import com.meghana.blogproject.poyload.CommentDTO;
import com.meghana.blogproject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable("postId") Long postId,
                                                    @RequestBody CommentDTO commentDto) {
        CommentDTO data = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable("postId") Long postId) {
        List<CommentDTO> data = commentService.getCommentByPostId(postId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("postId") Long postId,
                                                     @PathVariable("id") Long commentId){
        CommentDTO commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("postId") Long postId,
                                                    @PathVariable("id") Long commentId,
                                                    @RequestBody CommentDTO commentDto){
        CommentDTO updateComment = commentService.updateCommentById(postId, commentId, commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId,
                                                @PathVariable("id") Long commentId){
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
    }
}
