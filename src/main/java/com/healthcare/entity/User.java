/*
 *  This class is parally implemented.
    Annotate this class with proper annotaon to declare it as an enty class with id as primary key.
    Map this class with a users table.
    Generate the id using the IDENTITY strategy
 * 
 * 
 * BUSINESS VALIDATIONS
        3.1 USER
        ● Id must be of type id.
        ● Username should not be blank, min 4 and max 20 characters and unique in the system.
        ● Password should not be blank, min 6 and max 100 characters.
        ● Email should not be blank and must be of type email.
        ● First name should not be blank.
        ● Last name should not be blank.

 * 
 */

package com.healthcare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    
    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;
    
    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;


}

/*
 *      @NotBlank: You should use it for validating that a field is not just non-null 
 *                 but also not empty or containing only whitespace, at the application level 
 *                 (e.g., before persisting data to the database).
 *
 *       @Column(nullable = false): Use it to enforce that the column in the database will not accept null values. 
 *                                 This is useful when you want the database to enforce the rule.
 * 
 * 
 */