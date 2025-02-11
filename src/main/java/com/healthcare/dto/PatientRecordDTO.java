package com.healthcare.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientRecordDTO {
    	
    private Long id;

	@NotNull
	private Long userId;

	@NotNull
	private Long doctorId;

	@NotNull
	private LocalDate date;

	@NotBlank
	private String diagnosis;

	@NotBlank
	private String treatment;

    @NotNull
	private boolean aggedForReview;

}
