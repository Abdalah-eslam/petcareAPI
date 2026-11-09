package org.petcare.petcare.Request;

import lombok.Data;
import org.petcare.petcare.Models.Appointment;

import java.util.List;
@Data
public class BookAppointmentRequest {
    private Appointment appointment;
}
