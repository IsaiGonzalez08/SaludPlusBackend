package com.vji.aquaqaapi.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse {

    private String token;

    private String rol;
}
