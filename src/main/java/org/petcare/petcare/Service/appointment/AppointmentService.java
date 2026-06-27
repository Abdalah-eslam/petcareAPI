package org.petcare.petcare.Service.appointment;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Enums.AppointmentStatus;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
import org.petcare.petcare.Models.Appointment;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.appointmentRepository;
import org.petcare.petcare.Repository.userRepository;
import org.petcare.petcare.Request.AppointmentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService{
    private final appointmentRepository appointmentRepository;
    private final userRepository userRepository;

    @Override
    public Appointment addAppointment(Appointment appointment, long DoctorId, long petId) {

        Optional<User> doctor = userRepository.findById(DoctorId);
        Optional<User> patient = userRepository.findById(petId);

        if (doctor.isPresent() && patient.isPresent()) {
            appointment.AddDoctor(doctor.get());
            appointment.Addpatient(patient.get());
            appointment.setAppointmentNO("APPT-" + System.currentTimeMillis());
            appointment.setStatus(AppointmentStatus.PENDING);
            return appointmentRepository.save(appointment);
        } else {
            throw new ResourceNotFoundException("Doctor or Patient not found");
        }



    }

    @Override
    public Appointment updateAppointment(long appointmentId, AppointmentUpdateRequest request) {
        Appointment appointment = getAppointmentById(appointmentId);
        if (!Objects.equals(appointment.getStatus(), AppointmentStatus.PENDING)) {
            throw new IllegalStateException("Appointment is not in pending state , Sorry this Appointment can't be updated");
        }
        appointment.setReason(request.getReason());
        appointment.setDate(request.getDate());
        appointment.setTime(request.getTime());
        return appointmentRepository.save(appointment);

    }


    @Override
    public Appointment getAppointmentById(long id) {
        return appointmentRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public void deleteAppointment(long id) {
        Appointment Exist = getAppointmentById(id);
        if (Exist != null) {
            appointmentRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Appointment not found");
        }

    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentsByNO(String appointmentNO) {
        return appointmentRepository.findByAppointmentNO(appointmentNO );
    }
}
