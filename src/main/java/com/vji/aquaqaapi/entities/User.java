package com.vji.aquaqaapi.entities;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private Long phoneNumber;

    private String email;

    private String password;

    private String rol;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;




}
