package com.meghana.blogproject.services.Impl;

import com.meghana.blogproject.entity.Post;
import com.meghana.blogproject.payload.PostDTO;
import com.meghana.blogproject.payload.PostResponse;
import com.meghana.blogproject.repository.PostRepository;
import com.meghana.blogproject.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postrepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDTO createPost(PostDTO postdto) {
        Post post=dtotoentity(postdto);
        Post newpost=postrepository.save(post);
        return entitytodto(newpost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postrepository.findAll(pageable);

        // get content for page object
        List<Post> postList = posts.getContent();

        List<PostDTO> content = postList.stream().map(post -> entitytodto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
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
          PostDTO postdto = modelMapper.map(post, PostDTO.class);
//        PostDTO postdto=new PostDTO();
//        postdto.setId(post.getId());
//        postdto.setTitle(post.getTitle());
//        postdto.setDescription(post.getDescription());
//        postdto.setContent(post.getContent());
        return postdto;
    }

    //convert dto to entity
    public Post dtotoentity(PostDTO postdto){
        Post post = modelMapper.map(postdto, Post.class);
//        post.setId(postdto.getId());
//        post.setTitle(postdto.getTitle());
//        post.setDescription(postdto.getDescription());
//        post.setContent(postdto.getContent());
        return post;
    }
}
