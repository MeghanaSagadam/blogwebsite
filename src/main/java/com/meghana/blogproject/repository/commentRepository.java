package com.meghana.blogproject.repository;

import com.meghana.blogproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<Comment,Long> {
}
