package com.vji.aquaqaapi.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CreateAppointmentRequest {
    private String patientName;
    private String patientLastname;
    private String patientEmail;
    private Long patientPhoneNumber;
    private LocalDateTime appointmentDate;


    private String doctorEmail;
}
