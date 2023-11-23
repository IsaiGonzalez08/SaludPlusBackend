package com.vji.aquaqaapi.services;

import com.vji.aquaqaapi.controllers.dtos.requests.CreateUserRequest;
import com.vji.aquaqaapi.controllers.dtos.requests.UpdateUserRequest;
import com.vji.aquaqaapi.controllers.dtos.responses.BaseResponse;
import com.vji.aquaqaapi.controllers.dtos.responses.GetUserResponse;
import com.vji.aquaqaapi.entities.User;
import com.vji.aquaqaapi.repositories.IUserRepository;
import com.vji.aquaqaapi.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public BaseResponse get(Long id) {
        GetUserResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("User by ID")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse findByEmail(String email) {
        GetUserResponse response = from(getUserByEmail(email));

        return BaseResponse.builder()
                .data(response)
                .message("User by email")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public BaseResponse list() {
         List<GetUserResponse> response= repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Users list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = from(request);
        if (isEmailUnique(request.getEmail())){
            return BaseResponse.builder()
                    .data(from(repository.save(user)))
                    .message("User created correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.CREATED).build();
        }

        return BaseResponse.builder()
                .data("user already exist :V")
                .message("User's email already exist")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CONFLICT).build();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("user do not exist"));

    }


    @Override
    public BaseResponse update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id).orElseThrow(()->new RuntimeException("user do not exist"));
        user = update(user, request);
        GetUserResponse response = from(user);
        return BaseResponse.builder()
                .data(response)
                .message("user updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    private User update(User user, UpdateUserRequest request) {
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRol() == null){
            user.setRol("doctor");
        }else {
            user.setRol(request.getRol());
        }
        return repository.save(user);
    }


    private User from(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setPhoneNumber(request.getPhoneNumber());

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getRol() == null){
            user.setRol("doctor");
        }else {
            user.setRol(request.getRol());
        }

        return user;
    }

    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setPassword(user.getPassword());
        response.setRol(user.getRol());
        return response;
    }


    private GetUserResponse from(Long idUser) {
        return repository.findById(idUser)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    public boolean isEmailUnique(String email) {
        Optional<User> existingUser = repository.findByEmail(email);
        return existingUser.isEmpty();
    }



}
