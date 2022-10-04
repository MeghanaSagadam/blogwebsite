package com.meghana.blogproject.services;

import com.meghana.blogproject.payload.PostDTO;
import com.meghana.blogproject.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {
    PostDTO createPost(PostDTO postdto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDTO getPostById(Long id);
    PostDTO updatePostById(PostDTO postdto, Long id);
    void deletePostById(Long id);
}
