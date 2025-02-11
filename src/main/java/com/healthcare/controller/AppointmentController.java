/*
 * 
 */

package com.healthcare.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.service.AppointmentService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // GET -  /api/appointments/user/{userId} - Retrieves all appointments for a specic user 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentDTO>> getUserAppointments(@PathVariable Long userId){
        List<AppointmentDTO> allAppointments = appointmentService.getUserAppointments(userId);
        return ResponseEntity.ok(allAppointments);
    }

    // POST - /api/appointments - Schedules a new appointment
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        AppointmentDTO newAppointment = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.ok(newAppointment);
    }

    // PUT - /api/appointments/{appointmentId} - Updates details of an exisng appointment
    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long appointmentId, @RequestBody AppointmentDTO appointmentDTO){
        AppointmentDTO updateDetails = appointmentService.updateAppointment(appointmentId, appointmentDTO);
        return ResponseEntity.ok(updateDetails);
    }    

    // DELETE -  /api/appointments/{appointmentId} - Cancels a specic appointment
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Boolean> cancelAppointment(@PathVariable Long appointmentId){
        Boolean cancelAppointments = appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok(cancelAppointments);
    }

    // GET - /api/appointments/{appointmentId} - Retrieves details of a specic appointment
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentDetails(@PathVariable Long appointmentId){
        AppointmentDTO appointmentDetails = appointmentService.getAppointmentDetails(appointmentId);
        return ResponseEntity.ok(appointmentDetails);
    }

    // PUT - /api/appointments/reschedule/{appointmentId} - Reschedules an exisng appointment
    @PutMapping("/reschedule/{appointmentId}")
    public ResponseEntity<AppointmentDTO> rescheduleAppointment(@PathVariable Long appointmentId, @RequestBody AppointmentDTO appointmentDTO){
        AppointmentDTO reschedule = appointmentService.rescheduleAppointment(appointmentId, appointmentDTO);
        return ResponseEntity.ok(reschedule);
    }

    // GET - /api/appointments/status/{appointmentId} - Checks the status of a specic appointment
    @GetMapping("/status/{appointmentId}")
    public ResponseEntity<String> checkAppointmentStatus(@PathVariable Long appointmentId){
        String status = appointmentService.checkAppointmentStatus(appointmentId);
        return ResponseEntity.ok(status);
    }

    // GET - /api/appointments/date - Retrieves appointments scheduled for a specic date
    @GetMapping("/date")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDate(@PathVariable LocalDate date){
        List<AppointmentDTO> appointmentByDate = appointmentService.getAppointmentsByDate(date);
        return ResponseEntity.ok(appointmentByDate);
    }


    // GET - /api/appointments/doctor/{doctorId} - Retrieves all appointments associated with a specic doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctor(@PathVariable Long doctorId){
        List<AppointmentDTO> appointmentByDoctor = appointmentService.getAppointmentsByDoctor(doctorId);
        return ResponseEntity.ok(appointmentByDoctor);
    }

    // PUT - . /api/appointments/complete/{appointmentId} - Marks an appointment as completed
    @PutMapping("/complete/{appointmentId}")
    public ResponseEntity<AppointmentDTO> markAppointmentAsCompleted(@PathVariable Long appointmentId){
        AppointmentDTO markAppointment = appointmentService.markAppointmentAsCompleted(appointmentId);
        return ResponseEntity.ok(markAppointment);
    }


    // GET - /api/appointments/history/user/{userId} - Retrieves historical appointment data for a user
    public ResponseEntity<List<AppointmentDTO>> getAppointmentHistoryForUser(@PathVariable Long userId){
        List<AppointmentDTO> appointmentHistory = appointmentService.getAppointmentHistoryForUser(userId);
        return ResponseEntity.ok(appointmentHistory);
    }
}
