package com.healthcare.service;

import java.util.List;

import com.healthcare.dto.PatientRecordDTO;

public interface PatientRecordService {

	PatientRecordDTO createPatientRecord(PatientRecordDTO patientRecordDTO);

	List<PatientRecordDTO> getPatientRecordsByUser(Long userId);

	PatientRecordDTO updatePatientRecord(Long recordId, PatientRecordDTO patientRecordDTO);

	Boolean deletePatientRecord(Long recordId);

	PatientRecordDTO getPatientRecordDetails(Long recordId);

	List<PatientRecordDTO> getAllPatientRecords();

	List<PatientRecordDTO> searchPatientRecords(String query);

	List<PatientRecordDTO> getPatientRecordsByDoctor(Long doctorId);

	PatientRecordDTO flagPatientRecordForReview(Long recordId);
}
