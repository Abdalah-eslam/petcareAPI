package org.petcare.petcare.Response.Appointment;

import org.petcare.petcare.Enums.AppointmentStatus;

import java.time.LocalTime;

public record BookingResponse(
        Long appointmentId,
        LocalTime startTime,
        LocalTime endTime,
        AppointmentStatus status
) {}
