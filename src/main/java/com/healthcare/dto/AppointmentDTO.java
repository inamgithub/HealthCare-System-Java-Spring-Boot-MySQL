package com.healthcare.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AppointmentDTO {

    private Long id;

	@NotNull
	private Long userId;

	@NotNull
	private Long doctorId;

	@NotNull
	private LocalDateTime appointmentTime;

	@NotBlank
	private String status;

}
