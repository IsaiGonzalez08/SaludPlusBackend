package com.vji.aquaqaapi.controllers.dtos.responses;

import com.vji.aquaqaapi.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class GetAppointmentResponse {
    private Long id;

    private String patientName;
    private String patientLastname;
    private String patientEmail;
    private Long patientPhoneNumber;
    private String appointmentDate;

    private DoctorResponse doctor;
}
