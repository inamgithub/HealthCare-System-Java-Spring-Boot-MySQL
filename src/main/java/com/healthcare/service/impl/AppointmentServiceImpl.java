package com.healthcare.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entity.Appointment;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.AppointmentRepository;
import com.healthcare.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    // add
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO){
            // covert DTO to entity
            Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
            Appointment createAppointment = appointmentRepository.save(appointment);
            return modelMapper.map(createAppointment, AppointmentDTO.class);
    }

	public List<AppointmentDTO> getUserAppointments(Long userId){
        List<Appointment> appointments = appointmentRepository.findByUserId(userId);
        return appointments.stream()
                           .map(i -> modelMapper.map(userId, AppointmentDTO.class))
                           .collect(Collectors.toList());
    }


	public AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDTO){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
            appointment.setStatus(appointmentDTO.getStatus());
            appointment = appointmentRepository.save(appointment);
            return modelMapper.map(appointment, AppointmentDTO.class);
        }else{
            throw new NotFoundException("Appointment not found with id " + appointmentId);
        }   
    }

    //delete
	public Boolean cancelAppointment(Long appointmentId){
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if(appointment.isPresent()){
        appointmentRepository.deleteById(appointmentId);
        return true;
        }
        return false;
    }

	public AppointmentDTO getAppointmentDetails(Long appointmentId){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            return modelMapper.map(appointment, AppointmentDTO.class);
        }else{
            throw new NotFoundException("Appointment not found with id " + appointmentId);
        }
    }

    //update
	public AppointmentDTO rescheduleAppointment(Long appointmentId, AppointmentDTO appointmentDTO){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
            appointment.setStatus(appointmentDTO.getStatus());
            appointment = appointmentRepository.save(appointment);
            return modelMapper.map(appointment, AppointmentDTO.class);
        }else{
            throw new NotFoundException("Appointment not found with id " + appointmentId);
        }
    }

	public String checkAppointmentStatus(Long appointmentId){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            return appointment.getStatus();
        }else{
            throw new NotFoundException("Appointment not found with id " + appointmentId);
        }
    }

	public List<AppointmentDTO> getAppointmentsByDate(LocalDate date){
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(23,59,59);
        List<Appointment> appointment =  appointmentRepository.findAllAppointments(startDateTime, endDateTime);
        return appointment.stream().map(i ->  modelMapper.map(i, AppointmentDTO.class)).collect(Collectors.toList());
    }

	public List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId){
         List<Appointment> appointments = appointmentRepository.findByDoctorAndDate(doctorId, null, null);
         return appointments.stream().map(i ->  modelMapper.map(i, AppointmentDTO.class)).collect(Collectors.toList());
    }

	public AppointmentDTO markAppointmentAsCompleted(Long appointmentId){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus("Completed");
            return modelMapper.map(appointment, AppointmentDTO.class);
        }else{
            throw new NotFoundException("Appointment not found with id " + appointmentId);
        }
    }
    

	public List<AppointmentDTO> getAppointmentHistoryForUser(Long userId){
        List<Appointment> appointments = appointmentRepository.findByUserId(userId);
        if(appointments.isEmpty()){
            throw new NotFoundException("Appointment not found with id " + userId);
            
        }
        return appointments.stream().map(i ->  modelMapper.map(i, AppointmentDTO.class)).collect(Collectors.toList());
        
    }

}
