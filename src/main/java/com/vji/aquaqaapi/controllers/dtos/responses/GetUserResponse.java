package com.vji.aquaqaapi.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetUserResponse {
    private Long id;

    private String name;

    private String lastname;

    private Long phoneNumber;

    private String email;

    private String password;

    private String rol;
}
