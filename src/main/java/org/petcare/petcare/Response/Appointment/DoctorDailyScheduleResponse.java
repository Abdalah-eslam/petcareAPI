package org.petcare.petcare.Response.Appointment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public record DoctorDailyScheduleResponse(
        Long doctorId,
        LocalDate date,
        DayOfWeek dayOfWeek,
        List<ShiftResponse> shifts
) {}