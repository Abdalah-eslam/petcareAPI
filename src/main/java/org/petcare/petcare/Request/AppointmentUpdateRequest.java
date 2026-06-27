package org.petcare.petcare.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {
    private String reason ;
    private LocalDate date;
    private LocalTime time;
}
