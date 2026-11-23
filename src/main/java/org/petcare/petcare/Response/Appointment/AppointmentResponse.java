package org.petcare.petcare.Response.Appointment;

import org.petcare.petcare.Enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponse(
        Long appointmentId,
        String appointmentNO,
        Long doctorId,
        String doctorName,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        AppointmentStatus status
) {}
