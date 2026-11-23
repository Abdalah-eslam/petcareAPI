package org.petcare.petcare.Request;

import lombok.Data;
import org.petcare.petcare.Models.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public record BookAppointmentRequest(
        Long doctorId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String reason
) {}
