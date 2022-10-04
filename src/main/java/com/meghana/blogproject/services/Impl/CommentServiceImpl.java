package com.meghana.blogproject.services.Impl;

import com.meghana.blogproject.entity.Comment;
import com.meghana.blogproject.entity.Post;
import com.meghana.blogproject.poyload.CommentDTO;
import com.meghana.blogproject.repository.CommentRepository;
import com.meghana.blogproject.repository.PostRepository;
import com.meghana.blogproject.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentrepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentdto) {
        Comment comment=mapToEntity(commentdto);
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("resource not found"));
        comment.setPost(post);
        Comment comments=commentrepository.save(comment);
        return mapToDTO(comments);
    }

    @Override
    public List<CommentDTO> getCommentByPostId(Long postId) {
        List<Comment> comments = commentrepository.findByPostId(postId);
        return comments.stream().map((com) ->mapToDTO(com)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new RuntimeException("resource not found"));
        Comment comment=commentrepository.findById(commentId).orElseThrow(()->new RuntimeException("resource not found"));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new RuntimeException("comment dosent belong to this post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateCommentById(Long postId, Long commentId, CommentDTO commentdto) {
        Post post=postRepository.findById(postId).orElseThrow(()->new RuntimeException("resource not found"));
        Comment comment=commentrepository.findById(commentId).orElseThrow(()->new RuntimeException("resource not found"));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new RuntimeException("comment dosent belong to this post");
        }
        comment.setName(commentdto.getName());
        comment.setEmail(commentdto.getEmail());
        comment.setBody(commentdto.getBody());
        Comment commentUpdate=commentrepository.save(comment);
        return mapToDTO(commentUpdate);
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new RuntimeException("resource not found"));
        Comment comment=commentrepository.findById(commentId).orElseThrow(()->new RuntimeException("resource not found"));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new RuntimeException("comment dosent belong to this post");
        }
        commentrepository.delete(comment);

    }
    // convert Entity to DTO
    private CommentDTO mapToDTO(Comment comment) {
        CommentDTO commentDto=modelMapper.map(comment,CommentDTO.class);
//        CommentDTO commentDto = new CommentDTO();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    // convert DTO to Entity
    private Comment mapToEntity(CommentDTO commentDto) {
        Comment comment=modelMapper.map(commentDto,Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
