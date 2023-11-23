package com.vji.aquaqaapi.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateUserRequest {
    private String name;

    private String lastname;

    private Long phoneNumber;

    private String email;

    private String password;

    private String rol;
}
