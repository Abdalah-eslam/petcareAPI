package org.petcare.petcare.Service.appointment;

import org.petcare.petcare.Models.Appointment;
import org.petcare.petcare.Request.AppointmentUpdateRequest;

import java.util.List;

public interface IAppointmentService {
    Appointment addAppointment(Appointment appointment , long DoctorId , long petId);
    Appointment updateAppointment(long appointmentId , AppointmentUpdateRequest request );
    Appointment getAppointmentById(long id);
    void deleteAppointment(long id);
    List<Appointment> getAllAppointments();
  Appointment getAppointmentsByNO(String appointmentNO);
}
