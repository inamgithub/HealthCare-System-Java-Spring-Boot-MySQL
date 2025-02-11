package com.healthcare.service;

import java.time.LocalDate;
import java.util.List;

import com.healthcare.dto.AppointmentDTO;

public interface AppointmentService {

	AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);

	List<AppointmentDTO> getUserAppointments(Long userId);

	AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDTO);

	Boolean cancelAppointment(Long appointmentId);

	AppointmentDTO getAppointmentDetails(Long appointmentId);

	AppointmentDTO rescheduleAppointment(Long appointmentId, AppointmentDTO appointmentDTO);

	String checkAppointmentStatus(Long appointmentId);

	List<AppointmentDTO> getAppointmentsByDate(LocalDate date);

	List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId);

	AppointmentDTO markAppointmentAsCompleted(Long appointmentId);

	List<AppointmentDTO> getAppointmentHistoryForUser(Long userId);
}
