/*
    GET - /api/paent-records/user/{userId} - Retrieves all paent records for a specic user Hp 
    POST - /api/paent-records - Adds a new paent record to the system
    GET - /api/paent-records/{recordId} - Retrieves detailed informaon aBout a specic paent record
    PUT - /api/paent-records/{recordId} - Updates an exisng paent record
    DELETE - . /api/paent-records/{recordId} - Deletes a specic paent record
    GET - /api/paent-records Retrieves a list of all paent records in the system
    GET - /api/paent-records/search - Searches paent records based on a diagnosis
    GET - /api/paent-records/doctor/{doctorId} - Retrieves all paent records associated with a specic doctor
    PUT - /api/paent-records/ag/{recordId} - Flags a paent record for further review
 */



package com.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.PatientRecordDTO;
import com.healthcare.service.PatientRecordService;

@RestController
@RequestMapping("/api/patient-records")
public class PatientRecordController {

    @Autowired
    private PatientRecordService patientRecordService;

    // POST - /api/paent-records - Adds a new paent record to the system
    @PostMapping
    public ResponseEntity<PatientRecordDTO> createPatientRecord(@RequestBody PatientRecordDTO patientRecordDTO){
        PatientRecordDTO addRecord = patientRecordService.createPatientRecord(patientRecordDTO);
        return ResponseEntity.ok(addRecord);
    }

    // GET - /api/paent-records/user/{userId} - Retrieves all paent records for a specic user 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PatientRecordDTO>> getPatientRecordsByUser(@PathVariable Long userId){
        List<PatientRecordDTO> getRecordsByUser = patientRecordService.getPatientRecordsByUser(userId);
        return ResponseEntity.ok(getRecordsByUser);
    }


    // GET - /api/paent-records/{recordId} - Retrieves detailed informaon aBout a specic paent record
    @GetMapping("/{recordId}")
    public ResponseEntity<PatientRecordDTO> getPatientRecordDetails(@PathVariable Long recordId){
        PatientRecordDTO detailRecord = patientRecordService.getPatientRecordDetails(recordId);
        return ResponseEntity.ok(detailRecord);
    }

    // PUT - /api/paent-records/{recordId} - Updates an exisng paent record
    @PutMapping("/{recordId}")
    public ResponseEntity<PatientRecordDTO> updatePatientRecord(@PathVariable Long recordId, @RequestBody PatientRecordDTO patientRecordDTO){
        PatientRecordDTO updateRecord = patientRecordService.updatePatientRecord(recordId, patientRecordDTO);
        return ResponseEntity.ok(updateRecord);
    }

    // DELETE - . /api/paent-records/{recordId} - Deletes a specic paent record
    @DeleteMapping("/{recordId}")
    public ResponseEntity<Boolean> deletePatientRecord(@PathVariable Long recordId){
        Boolean isDeleted = patientRecordService.deletePatientRecord(recordId);
        return ResponseEntity.ok(isDeleted);
    }

    // GET - /api/paent-records Retrieves a list of all paent records in the system
    @GetMapping
    public ResponseEntity<List<PatientRecordDTO>> getAllPatientRecords(){
        List<PatientRecordDTO> records = patientRecordService.getAllPatientRecords();
        return ResponseEntity.ok(records);
    }

    // GET - /api/paent-records/search - Searches paent records based on a diagnosis
    @GetMapping("/search")
    public ResponseEntity<List<PatientRecordDTO>> searchPatientRecords(@RequestParam String query){  
        List<PatientRecordDTO> searchRecord = patientRecordService.searchPatientRecords(query);
        return ResponseEntity.ok(searchRecord);
    }  

    // GET - /api/paent-records/doctor/{doctorId} - Retrieves all paent records associated with a specic doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PatientRecordDTO>> getPatientRecordsByDoctor(@PathVariable Long doctorId){
        List<PatientRecordDTO> recordsByDoctor = patientRecordService.getPatientRecordsByDoctor(doctorId);
        return ResponseEntity.ok(recordsByDoctor);
    }

    // PUT - /api/paent-records/ag/{recordId} - Flags a paent record for further review
    @PutMapping("/flag/{recordId}")
    public ResponseEntity<PatientRecordDTO> flagPatientRecordForReview(@PathVariable Long recordId){
        PatientRecordDTO flagPatient = patientRecordService.flagPatientRecordForReview(recordId);
        return ResponseEntity.ok(flagPatient);
    }    




}
