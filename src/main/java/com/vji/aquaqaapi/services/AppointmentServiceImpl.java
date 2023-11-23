package com.vji.aquaqaapi.services;

import com.vji.aquaqaapi.controllers.dtos.requests.CreateAppointmentRequest;
import com.vji.aquaqaapi.controllers.dtos.responses.BaseResponse;
import com.vji.aquaqaapi.controllers.dtos.responses.DoctorResponse;
import com.vji.aquaqaapi.controllers.dtos.responses.GetAppointmentResponse;
import com.vji.aquaqaapi.entities.Appointment;
import com.vji.aquaqaapi.entities.User;
import com.vji.aquaqaapi.repositories.IAppointmentRepository;
import com.vji.aquaqaapi.services.interfaces.IAppointmentService;
import com.vji.aquaqaapi.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IUserService userService;


    @Override
    public BaseResponse get(Long id) {
        GetAppointmentResponse response = from(appointmentRepository.getById(id));
        return BaseResponse.builder()
                .data(response)
                .message("appointment by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateAppointmentRequest request) {
        Appointment appointment = from(request);
        if (isDateUnique(from(request.getAppointmentDate()))){
            GetAppointmentResponse response = from(appointmentRepository.save(appointment));
            return BaseResponse.builder()
                    .data(response)
                    .message("appointment created")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }
        return BaseResponse.builder()
                .data("date already busy :V")
                .message("User's date cannot be selected")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CONFLICT).build();


    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);

    }


    private Appointment from (CreateAppointmentRequest request){
        Appointment appointment = new Appointment();
        appointment.setPatientName(request.getPatientName());
        appointment.setPatientLastname(request.getPatientLastname());
        appointment.setPatientPhoneNumber(request.getPatientPhoneNumber());
        appointment.setPatientEmail(request.getPatientEmail());
        appointment.setDoctor(userService.getUserByEmail(request.getDoctorEmail()));

        appointment.setAppointmentDate(from(request.getAppointmentDate()));
        return appointment;
    }

    private String from (LocalDateTime appointmentDate){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");
        return appointmentDate.format(myFormatObj);
    }

    private GetAppointmentResponse from(Appointment appointment){
        GetAppointmentResponse response = new GetAppointmentResponse();
        response.setId(appointment.getId());
        response.setPatientName(appointment.getPatientName());
        response.setPatientLastname(appointment.getPatientLastname());
        response.setPatientPhoneNumber(appointment.getPatientPhoneNumber());
        response.setPatientEmail(appointment.getPatientEmail());
        response.setDoctor(from(appointment.getDoctor()));
        response.setAppointmentDate(appointment.getAppointmentDate());
        return response;
    }


    private DoctorResponse from (User user){
        DoctorResponse response = new DoctorResponse();
        response.setName(user.getName());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        return response;
    }

    public boolean isDateUnique(String appointmentDate) {
        Optional<Appointment> existingAppointment = appointmentRepository.findByAppointmentDate(appointmentDate);
        return existingAppointment.isEmpty();
    }
}
