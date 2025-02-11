package com.healthcare.service;

import java.util.List;

import com.healthcare.dto.UserDTO;

public interface UserService {

	UserDTO registerUser(UserDTO userDTO);

	UserDTO updateUserProfile(Long userId, UserDTO userDTO);

	UserDTO getUserDetails(Long userId);

	Boolean deleteUser(Long userId);

	List<UserDTO> getAllUsers();

	List<UserDTO> searchUsers(String query);
}
