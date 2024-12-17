package com.example.muskan.service;


import com.example.muskan.dto.UserDto;
import com.example.muskan.entity.User;
import com.example.muskan.exception.UserNotFoundException;
import com.example.muskan.repository.PostRepository;
import com.example.muskan.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

   
    public UserDto createUser(UserDto userDto) {
        User user = DtotoUser(userDto);
        userRepository.save(user);
        return UsertoDto(user);
    }
   
    public UserDto UpdateUser(UserDto userDto, String UserId) {
        User user = userRepository.findById(UserId).
                orElseThrow(()->new UserNotFoundException("userid"+UserId+" not found"));
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        userRepository.save(user);
        return UsertoDto(user);
    }
   
    public UserDto getUserbyId(String UserId) {
        User user = userRepository.findById(UserId).
                orElseThrow(()->new UserNotFoundException("userid"+UserId+" not found"));
        return UsertoDto(user);
    }
   
    public List<UserDto> getallUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersList=
                users.stream().map(user->UsertoDto(user)).
                        collect(Collectors.toList());
        return usersList;
    }
   
    public void deleteUser(String UserId) {
        User user = userRepository.findById(UserId).
                orElseThrow(()->new UserNotFoundException("userid"+UserId+" not found"));
        userRepository.delete(user);
    }
    private UserDto UsertoDto(User user)
    {
        UserDto userDto = new UserDto(user.getName(), user.getId());
        return userDto;
    }
    private User DtotoUser(UserDto userDto)
    {
        User user = new User(userDto.getName(), userDto.getId());
        return user;
    }



}
