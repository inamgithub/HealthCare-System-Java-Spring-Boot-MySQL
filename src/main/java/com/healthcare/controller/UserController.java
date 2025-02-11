/*
 *  GET - /api/users/{userId} - Retrieves details of a user by their ID
 *  POST -  /api/users - Register a new user
 *  PUT - /api/users/{userId} - Updates the prole details of an exisng user
 *  DELETE - /api/users/{userId} - Deletes a user from the system by their ID
 *  GET -  /api/users - Retrieves a list of all registered users
 *  GET - /api/users/search - Searches for users based on username
 */



package com.healthcare.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.healthcare.dto.UserDTO;
import com.healthcare.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET -  /api/users - Retrieves a list of all registered users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    
    //POST -  /api/users - Register a new user
    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@Validated @RequestBody UserDTO userDTO){
        UserDTO registerUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registerUser);
    }


    //  GET - /api/users/{userId} - Retrieves details of a user by their ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId){
        UserDTO userDTO = userService.getUserDetails(userId);
        return ResponseEntity.ok(userDTO);
    }

    

    // PUT - /api/users/{userId} - Updates the prole details of an exisng user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable Long userId, @Validated @RequestBody UserDTO userDTO){
        UserDTO updateUser = userService.updateUserProfile(userId, userDTO);
        return ResponseEntity.ok(updateUser);
    }    


    // GET - /api/users/search - Searches for users based on username
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String query){
        List<UserDTO> searchUser = userService.searchUsers(query);
        return ResponseEntity.ok(searchUser);
    }    

    //DELETE - /api/users/{userId} - Deletes a user from the system by their ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        Boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
    }
}
