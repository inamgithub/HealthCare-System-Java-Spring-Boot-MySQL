


package com.healthcare.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    
    private Long id;
    
    
    @NotBlank
    @Size(min = 4, max=20)
    private String username;

    @NotBlank
    @Size(min = 6, max=100)
    private String password;

    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
