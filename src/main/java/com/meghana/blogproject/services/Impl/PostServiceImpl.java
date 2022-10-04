package com.meghana.blogproject.services.Impl;

import com.meghana.blogproject.entity.Post;
import com.meghana.blogproject.poyload.PostDTO;
import com.meghana.blogproject.repository.PostRepository;
import com.meghana.blogproject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postrepository;
    @Override
    public PostDTO createPost(PostDTO postdto) {
        Post post=dtotoentity(postdto);
        Post newpost=postrepository.save(post);
        return entitytodto(newpost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts=postrepository.findAll();
        return posts.stream().map(pos -> entitytodto(pos)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post=postrepository.findById(id)
                .orElseThrow(()->new RuntimeException("resource not found"));
        return entitytodto(post);
    }

    @Override
    public PostDTO updatePostById(PostDTO postdto, Long id) {
        Post post=postrepository.findById(id)
                .orElseThrow(()->new RuntimeException("resource not found"));
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        Post newpost=postrepository.save(post);
        return entitytodto(newpost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post=postrepository.findById(id)
                .orElseThrow(()->new RuntimeException("resource not found"));
        postrepository.delete(post);
    }
    //convert entity to dto
    public PostDTO entitytodto(Post post){
        PostDTO postdto=new PostDTO();
        postdto.setId(post.getId());
        postdto.setTitle(post.getTitle());
        postdto.setDescription(post.getDescription());
        postdto.setContent(post.getContent());
        return postdto;
    }

    //convert dto to entity
    public Post dtotoentity(PostDTO postdto){
        Post post=new Post();
        post.setId(postdto.getId());
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        return post;
    }
}
