package com.healthcare.service;

import java.util.List;

import com.healthcare.dto.DoctorDTO;

public interface DoctorService {

	DoctorDTO createDoctor(DoctorDTO doctorDTO);

	DoctorDTO getDoctorById(Long doctorId);

	List<DoctorDTO> getAllDoctors();
}
