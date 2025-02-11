/*
 *  ● Repository interface exposing CRUD funconality for User Enty.
    ● It must contain the methods for:
        ○ Finding all users by their username.
        ○ Finding all users by email.
    ● You can go ahead and add any custom methods as per requirements.

 */



package com.healthcare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom methods removed. Now you can practice implementing them.

   
    // find user by their name
    User findByUsername(String username);

    // find all usres whose username contail some specific keyword
    List<User> findByUsernameContainingIgnoreCase(String username);

   // find user by their email
    User findByEmail(String email);

    // // Find all users by email domain (custom JPQL query)
    // @Query("SELECT u FROM User u WHERE u.email LIKE %?1%")
    // List<User> findByEmailDomain(String domain);

}
