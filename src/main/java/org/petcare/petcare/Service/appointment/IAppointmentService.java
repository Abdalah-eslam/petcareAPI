package org.petcare.petcare.Service.appointment;

import org.petcare.petcare.Models.Appointment;
import org.petcare.petcare.Request.AppointmentUpdateRequest;
import org.petcare.petcare.Request.BookAppointmentRequest;
import org.petcare.petcare.Response.Appointment.AppointmentResponse;

import java.util.List;

public interface IAppointmentService {
    AppointmentResponse addAppointment(BookAppointmentRequest request );
    Appointment updateAppointment(long appointmentId , AppointmentUpdateRequest request );
    Appointment getAppointmentById(long id);
    void deleteAppointment(long id);
    List<Appointment> getAllAppointments();
  Appointment getAppointmentsByNO(String appointmentNO);
}
