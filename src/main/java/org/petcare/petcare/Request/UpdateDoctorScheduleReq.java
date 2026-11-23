package org.petcare.petcare.Request;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record UpdateDoctorScheduleReq( DayOfWeek dayOfWeek
        , LocalTime startTime , LocalTime endTime
        , Integer slotDuration) {
}
