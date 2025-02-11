/*
 *  ● When updang a paent record, if the record ID does not exist, the service method should
    throw a NotFoundExcepon with the message "Paent record not found with id [recordId]".
    
    ● When fetching paent record details by ID, if the paent record ID does not exist, the service
    method should throw a NotFoundExcepon with the message "Paent record not found with id
    [recordId]".
    
    ● When agging a paent record for review, if the record ID does not exist, the service method
    should throw a NotFoundExcepon with the message "Paent record not found with id
    [recordId]".
 */



package com.healthcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dto.PatientRecordDTO;
import com.healthcare.entity.PatientRecord;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.PatientRecordRepository;
import com.healthcare.service.PatientRecordService;

@Service
public class PatientRecordServiceImpl implements PatientRecordService{

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    @Autowired
    private ModelMapper modelMapper;


    public PatientRecordDTO createPatientRecord(PatientRecordDTO patientRecordDTO){

        PatientRecord patientRecord = modelMapper.map(patientRecordDTO, PatientRecord.class);
        PatientRecord createRecord =  patientRecordRepository.save(patientRecord);
        return modelMapper.map(createRecord, PatientRecordDTO.class);
    }

    public List<PatientRecordDTO> getPatientRecordsByUser(Long userId){
        
            List<PatientRecord> records = patientRecordRepository.findByUserAndAggedForReview(userId);
            return records.stream().map(i -> modelMapper.map(records, PatientRecordDTO.class)).collect(Collectors.toList());                  
        
    }

	public PatientRecordDTO updatePatientRecord(Long recordId, PatientRecordDTO patientRecordDTO){
        Optional<PatientRecord> record = patientRecordRepository.findById(recordId);
        if(record.isPresent()){
            PatientRecord p = record.get();
            p.setDate(patientRecordDTO.getDate());
            p.setDiagnosis(patientRecordDTO.getDiagnosis());
            p.setTreatment(patientRecordDTO.getTreatment());
            p.setAggedForReview(patientRecordDTO.isAggedForReview());
            p = patientRecordRepository.save(p);
            return modelMapper.map(p, PatientRecordDTO.class);
        }
        else{
            throw  new NotFoundException("Patient record not found with id " + recordId);   
        }
    }

	public Boolean deletePatientRecord(Long recordId){
        Optional<PatientRecord> record = patientRecordRepository.findById(recordId);
        if(record.isPresent()){
            patientRecordRepository.deleteById(recordId);
            return true;
         }
        return false;
    }

	public PatientRecordDTO getPatientRecordDetails(Long recordId){
        Optional<PatientRecord> record = patientRecordRepository.findById(recordId);
        if(record.isPresent()){            
            return modelMapper.map(record.get(), PatientRecordDTO.class);
        }else{
            throw  new NotFoundException("Patient record not found with id " + recordId);   

        }
    }

	public List<PatientRecordDTO> getAllPatientRecords(){
        List<PatientRecord> records = patientRecordRepository.findAll();
        return records.stream()
                      .map(i -> modelMapper.map(i, PatientRecordDTO.class))
                      .collect(Collectors.toList());
    }

	public List<PatientRecordDTO> searchPatientRecords(String query){
        List<PatientRecord> records = patientRecordRepository.findByDiagnosis(query);
        return records.stream().map(i -> modelMapper.map(i, PatientRecordDTO.class)).collect(Collectors.toList());
    }

	public List<PatientRecordDTO> getPatientRecordsByDoctor(Long doctorId){
        List<PatientRecord> records = patientRecordRepository.findByDoctorId(doctorId);
        return records.stream()
                       .map(record -> modelMapper.map(record, PatientRecordDTO.class))
                        .collect(Collectors.toList());
    }

	public PatientRecordDTO flagPatientRecordForReview(Long recordId){
        Optional<PatientRecord> record = patientRecordRepository.findById(recordId);
        if(record.isPresent()){            
            PatientRecord records = record.get();
            records.setAggedForReview(true);
            records = patientRecordRepository.save(records);
            return modelMapper.map(records, PatientRecordDTO.class);
        }else{
            throw new NotFoundException("Patient record not found with id " + recordId);

        }
    }

}
