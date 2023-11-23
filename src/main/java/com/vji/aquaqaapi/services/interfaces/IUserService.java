package com.vji.aquaqaapi.services.interfaces;

import com.vji.aquaqaapi.controllers.dtos.requests.CreateUserRequest;
import com.vji.aquaqaapi.controllers.dtos.requests.UpdateUserRequest;
import com.vji.aquaqaapi.controllers.dtos.responses.BaseResponse;
import com.vji.aquaqaapi.entities.User;

public interface IUserService {
    BaseResponse get(Long id);

    BaseResponse findByEmail(String email);

    BaseResponse list();

    BaseResponse create(CreateUserRequest request);

    BaseResponse update(Long id, UpdateUserRequest request);

    User findById(Long id);

    User getUserByEmail(String email);

    void delete(Long id);
}
