package com.meghana.blogproject.repository;

import com.meghana.blogproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
