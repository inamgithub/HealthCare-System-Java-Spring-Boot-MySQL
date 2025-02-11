/*
        ● Implements UserService.
        ● Contains template method implementaon.
        ● Need to provide implementaon for user related funconalies.
        ● Do not modify, add or delete any method signature

        ● When updang a user's prole, if the user ID does not exist, the service method should 
            throw a NotFoundExcepon with the message "User not found with id [userId]".

        ● When fetching user details by ID, if the user ID does not exist, the service method should
            throw a NotFoundExcepon with the message "User not found with id [userId]".


        User Module Funconalies
            1 Register a user
            2 Get user details by id
            3 Update an user by id
            4 Delete an user by id
            5 Get all users
            6 Search users by their username
 */

package com.healthcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dto.UserDTO;
import com.healthcare.entity.User;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.UserRepository;
import com.healthcare.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    // private UserDTO convertToDTO(User user){
    //     UserDTO userDTO = new UserDTO();
    //     userDTO.setId(user.getId());
    //     userDTO.
    // }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    // register a user
    // @Override
    public UserDTO registerUser(UserDTO userDTO){
        // UserDTO is meant for data transfer, while UserRepository works with entity objects.
        // so You should convert UserDTO to UserEntity before saving.
        // Convert the saved UserEntity back to UserDTO before returning.
        // User user = convertToEntity(userDTO);
        // user = userRepository.save(user);
        // return convertToDTO(user);

        User user = modelMapper.map(userDTO, User.class);
        User registerUser = userRepository.save(user);
        return modelMapper.map(registerUser, UserDTO.class);
    }


    


    //get user details by id 
    public  UserDTO getUserDetails(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return modelMapper.map(optionalUser.get(), UserDTO.class);
        }else{
            throw new NotFoundException("User not found with id " + id);
        }
    }

 //  3 Update an user by id
 public UserDTO updateUserProfile(Long userId, UserDTO userDTO){
    Optional<User> optionalUser = userRepository.findById(userId);
    if(optionalUser.isPresent()){
        User u = optionalUser.get();
        u.setUsername(userDTO.getUsername());
        u.setPassword(userDTO.getPassword());
        u.setEmail(userDTO.getEmail());
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        User user = userRepository.save(u);
        return modelMapper.map(user, UserDTO.class);
    }else{
        throw new NotFoundException("User not found with id " + userId);
    }
}
   


    // 4 Delete an user by id
    public Boolean deleteUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return  false;
    }


    // 5 Get all users
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());
    }


    // 6 Search users by their username
    public List<UserDTO> searchUsers(String query){
        List<User> user = userRepository.findByUsernameContainingIgnoreCase(query);
        return user.stream()
                   .map(i -> modelMapper.map(i,UserDTO.class))
                   .collect(Collectors.toList());
    }


}
