package org.petcare.petcare.Response.Appointment;

import java.time.LocalTime;
import java.util.List;

public record ShiftResponse(
        LocalTime startTime,
        LocalTime endTime,
        Integer slotDuration,
        List<BookingResponse> bookings
) {}