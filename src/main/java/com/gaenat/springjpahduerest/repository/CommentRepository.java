package com.gaenat.springjpahduerest.repository;

import com.gaenat.springjpahduerest.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAll();
}