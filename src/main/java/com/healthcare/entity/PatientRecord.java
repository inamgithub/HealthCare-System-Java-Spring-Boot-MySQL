/*
        * This class is parally implemented.
        ● Annotate this class with proper annotaon to declare it as an enty class with id as primary key.
        ● Map this class with a paent_records table.
        ● Generate the id using the IDENTITY strategy

        
        ● Id must be of type id.
        ● User ID should not be null.
        ● Doctor ID should not be null.
        ● Date should not be null.
        ● Diagnosis should not be blank.
        ● Treatment should not be blank.
        ● aggedForReview should not be null.



 */





package com.healthcare.entity;

import java.time.LocalDate;

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
@Table(name = "patient_records")
public class PatientRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    @NotNull
    private LocalDate date;

    @NotNull
    private String diagnosis;

    @NotNull
    private String treatment;

    @NotNull
    private boolean aggedForReview;


}
