package com.example.muskan.repository;

import com.example.muskan.entity.Post;
import com.example.muskan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
}