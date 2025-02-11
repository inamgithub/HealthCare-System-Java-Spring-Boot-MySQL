/*
    ● Repository interface exposing CRUD funconality for PaentRecord Enty.
    ● It must contain the methods for:
        ○ Finding all paent records by agged records by user.
        ○ Finding all paent records by diagnosis.
    ● You can go ahead and add any custom methods as per requirements.

 */



package com.healthcare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.healthcare.entity.PatientRecord;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {


	//  Finding all patient records by agged records by user.
    // List<PatientRecord> findByUserAndAggedForReview(User user, Boolean aggedForReview);
    	@Query("SELECT pr FROM PatientRecord pr WHERE pr.user.id = ?1 AND pr.aggedForReview = true")
        List<PatientRecord> findByUserAndAggedForReview(Long userId);



    //finding all patient records by diagnisis
    List<PatientRecord> findByDiagnosis(String diagnosis);

    List<PatientRecord> findByDoctorId(Long doctorId);


}
