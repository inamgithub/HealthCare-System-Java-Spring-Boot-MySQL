/*
 *  DOCTOR
        ● Id must be of type id.
        ● Name should not be blank.
        ● Speciality should not be blank.

        

       This class is parally implemented.
       ● Annotate this class with proper annotaon to declare it as an enty class with id as primary key.
       ● Map this class with a doctors table.
       ● Generate the id using the  IDENTITY strategy
 */





package com.healthcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "doctors")
public class Doctor {

       @Id
       @GeneratedValue( strategy = GenerationType.IDENTITY)
       private Long id;

       
       @Column(nullable = false )
       private String name;

       
       @Column(nullable = false )
       private String speciality;

}
