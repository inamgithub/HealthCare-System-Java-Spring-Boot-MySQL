package com.healthcare.dto;

import jakarta.validation.constraints.NotBlank;

public class DoctorDTO {
    
    private Long id;

       
    @NotBlank
    private String name;

       
    @NotBlank
    private String speciality;


}
