package com.sdg.goals.service;

import java.util.List;

import com.sdg.goals.dto.UserDTO;

public interface UserService {

    
   
public UserDTO getUserById(Long id);
    
    public UserDTO getUserByUsername(String username);
    
    public boolean isUserAuthorized(String username, String password);
    
    public UserDTO createUser(UserDTO userDTO);
    
    public void deleteUserById(Long id);
    
    public List<UserDTO> getAllUsers();
    
    public UserDTO updateUser(Long id, UserDTO userDTO);
}
