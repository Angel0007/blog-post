package com.example.muskan.service;

import com.example.muskan.dto.PostDto;
import com.example.muskan.entity.Post;
import com.example.muskan.entity.User;
import com.example.muskan.repository.PostRepository;
import com.example.muskan.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    UserRepository userRepository;
    PostRepository postRepository;
    ModelMapper modelMapper;
 
    
    public PostDto CreatePost(PostDto postDto, String userID) {
        User user = userRepository.findById(userID).
                orElseThrow(()->new ResourceNotFoundException("user not found"));
        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);
        postRepository.save(post);
        return modelMapper.map(post,PostDto.class);
    }
    
    public List<PostDto> getAllPost() {
        List<Post> posts=  postRepository.findAll(); List<PostDto> postDtos=posts.stream().map(p->modelMapper.
                map(p,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    /*
       
       public PostDto UpdatePost(PostDto postDto, int post_id) {
           return null;
       }
       
       public void DeletePost(PostDto postDto) {
       }
       */
    
    public List<PostDto> getPostbyUser(String UserId) {
        User user= userRepository.findById(UserId).
                orElseThrow(()->new ResourceNotFoundException("user not found"));
        List<Post> posts= user.getPosts();
        List<PostDto> postDtos=posts.stream().map(p->modelMapper.
                map(p,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

}


