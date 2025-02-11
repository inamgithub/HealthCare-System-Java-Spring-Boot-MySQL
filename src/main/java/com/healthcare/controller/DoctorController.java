/*

    POST - /api/doctors - Adds a new doctor to the system
    GET - /api/doctors/{doctorId} - Retrieves details of a specic doctor by their ID
    GET - 3. /api/doctors - Retrieves a list of all doctors

 */


package com.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.healthcare.dto.DoctorDTO;
import com.healthcare.service.DoctorService;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // add new doctor
    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO){
        DoctorDTO addDoctor =  doctorService.createDoctor(doctorDTO);
        return ResponseEntity.ok(addDoctor);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long doctorId){
        DoctorDTO retrieveDoctorById = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(retrieveDoctorById);
    }    

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors(){
        List<DoctorDTO> allDoctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(allDoctors);
    }
}
