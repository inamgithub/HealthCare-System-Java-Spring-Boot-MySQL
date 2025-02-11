/*
     APPOINTMENT
        ● Id must be of type id.
        ● User ID should not be null.
        ● Doctor ID should not be null.
        ● appointmentTime should not be null.
        ● Status should not be blank.

        ● This class is parally implemented.
        ● Annotate this class with proper annotaon to declare it as an enty class with id as primary key.
        ● Map this class with an appointments table.
        ● Generate the id using the IDENTITY strategy

 */




package com.healthcare.entity;

import java.time.LocalDateTime;
import java.lang.String;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    
    private LocalDateTime appointmentTime;

    
    private String status;
    


}
