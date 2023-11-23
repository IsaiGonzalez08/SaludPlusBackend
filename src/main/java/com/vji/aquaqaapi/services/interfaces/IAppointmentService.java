package com.vji.aquaqaapi.services.interfaces;

import com.vji.aquaqaapi.controllers.dtos.requests.CreateAppointmentRequest;
import com.vji.aquaqaapi.controllers.dtos.requests.CreateUserRequest;
import com.vji.aquaqaapi.controllers.dtos.responses.BaseResponse;

public interface IAppointmentService {
    BaseResponse get(Long id);

    BaseResponse create(CreateAppointmentRequest request);

    void delete(Long id);
}
