package com.vji.aquaqaapi.repositories;

import com.vji.aquaqaapi.entities.Appointment;
import com.vji.aquaqaapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentDate(String appointmentDate);
}
