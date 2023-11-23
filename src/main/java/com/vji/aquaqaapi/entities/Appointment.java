package com.vji.aquaqaapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String patientLastname;
    private String patientEmail;
    private Long patientPhoneNumber;
    private String appointmentDate;


    //aca poner lo de la relación con el doc, N citas a 1 doctor
    @ManyToOne
    private User doctor;




}
