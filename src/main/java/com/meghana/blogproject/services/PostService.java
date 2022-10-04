package com.meghana.blogproject.services;

import com.meghana.blogproject.poyload.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {
    PostDTO createPost(PostDTO postdto);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long id);
    PostDTO updatePostById(PostDTO postdto, Long id);
    void deletePostById(Long id);
}
