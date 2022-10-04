package com.meghana.blogproject.controller;

import com.meghana.blogproject.entity.Post;
import com.meghana.blogproject.poyload.PostDTO;
import com.meghana.blogproject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<PostDTO>createPost(@RequestBody PostDTO postDTO){
        PostDTO data=postService.createPost(postDTO);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PostDTO>>getAllPosts(){
        List<PostDTO> data=postService.getAllPosts();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO>getPostById(@PathVariable("id") Long id){
        PostDTO data=postService.getPostById(id);
        if(data==null){
            return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO>updatePostById(@RequestBody PostDTO postDTO,@PathVariable("id") Long id){
        PostDTO data=postService.updatePostById(postDTO,id);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("post delete successfully", HttpStatus.OK);
    }
}
