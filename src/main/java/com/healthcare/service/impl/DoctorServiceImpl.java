/*
 * Doctor Module Funconalies
        1 Create doctor
        2 Get doctor by id
        3 Get all doctors

    When fetching a doctor by ID, if the doctor ID does not exist, the service method should throw a
NotFoundExcepon with the message "Doctor not found with id [doctorId]".


 */


package com.healthcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.healthcare.dto.DoctorDTO;
import com.healthcare.entity.Doctor;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.DoctorRepository;
import com.healthcare.service.DoctorService;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DoctorDTO createDoctor(DoctorDTO doctorDTO){

        Doctor doc = modelMapper.map(doctorDTO, Doctor.class);
        Doctor doctor = doctorRepository.save(doc);

        return modelMapper.map(doctor, DoctorDTO.class);
    }

	public DoctorDTO getDoctorById(Long doctorId){
        Optional<Doctor> docs = doctorRepository.findById(doctorId);
        if(docs.isPresent()){
            return modelMapper.map(docs.get(), DoctorDTO.class); 
        }else{
            throw new NotFoundException("Doctor not found with id "+ doctorId);
        }
    }

	public List<DoctorDTO> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                      .map(i -> modelMapper.map(doctors, DoctorDTO.class))
                      .collect(Collectors.toList());
    }

}
