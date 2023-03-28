package com.sdg.goals.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdg.goals.dto.UserDTO;
import com.sdg.goals.model.User;
import com.sdg.goals.repository.UserRepository;
import com.sdg.goals.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? new UserDTO(user.get().getUsername(), user.get().getPassword()) : null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? new UserDTO(user.getUsername(), user.getPassword()) : null;
    }

    @Override
    public boolean isUserAuthorized(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUsername(), savedUser.getPassword());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.getUsername(), user.getPassword()));
        }
        return userDTOs;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            User savedUser = userRepository.save(user);
            return new UserDTO(savedUser.getUsername(), savedUser.getPassword());
        } else {
            return null;
        }
    }

}
