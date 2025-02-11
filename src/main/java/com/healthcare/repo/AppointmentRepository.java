/*
    ● Repository interface exposing CRUD funconality for Appointment Enty.
    ● It must contain the methods for:
        ○ Finding all appointments by doctor and date in range.
        ○ Finding all appointments by date range.
        ○ Finding all appointments by user id.
    ● You can go ahead and add any custom methods as per requirements.

 */



package com.healthcare.repo;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.healthcare.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	// Finding all appointments by doctor and date in range.
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND (:startDateTime IS NULL OR a.appointmentTime >= :startDateTime) AND (:endDateTime IS NULL OR a.appointmentTime <= :endDateTime)")
    List<Appointment> findByDoctorAndDate(@Param("doctorId") Long doctorId, 
                                          @Param ("startDateTime") LocalDateTime startDateTime, 
                                          @Param ("endDateTime") LocalDateTime endDateTime);


    // Finding all appointments by date range.
    @Query("SELECT a FROM Appointment a WHERE a.appointmentTime BETWEEN :startDateTime AND :endDateTime")
    List<Appointment> findAllAppointments(@Param ("startDateTime") LocalDateTime startDateTime,
                                          @Param ("endDateTime") LocalDateTime endDateTime);


    // Finding all appointments by user id.
    List<Appointment> findByUserId(Long userId);
}
