package com.vji.aquaqaapi.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {

    private String name;

    private String lastname;

    private Long phoneNumber;

    private String email;

    private String password;

    private String rol;
}
