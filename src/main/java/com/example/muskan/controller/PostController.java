package com.example.muskan.controller;


import com.example.muskan.dto.PostDto;
import com.example.muskan.service.PostService;
import com.example.muskan.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/blog/")
public class PostController {
    UserService userService;
    PostService postService;
    public PostController(UserService userService,PostService postService)
    {
        this.postService=postService;
        this.userService=userService;
    }
    @PostMapping("user/{userId}/post/")
    public ResponseEntity<PostDto> createPost(@PathVariable String userId, @RequestBody PostDto postDto)
    {
        PostDto postDto1 = postService.CreatePost(postDto,userId);
        return new  ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getpostbyuser(@PathVariable String userId)
    {
        List<PostDto> postDtos= postService.getPostbyUser(userId);
        return  new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts()
    {
        List<PostDto> postDtos= postService.getAllPost();
        return  new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
