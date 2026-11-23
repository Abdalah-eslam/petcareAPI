package org.petcare.petcare.Service.appointment;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Enums.AppointmentStatus;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
import org.petcare.petcare.Models.Appointment;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.Appointment.appointmentRepository;
import org.petcare.petcare.Repository.Users.userRepository;
import org.petcare.petcare.Request.AppointmentUpdateRequest;
import org.petcare.petcare.Request.BookAppointmentRequest;
import org.petcare.petcare.Response.Appointment.AppointmentResponse;
import org.petcare.petcare.Service.User.userService;
import org.petcare.petcare.Service.doctorSchedule.DoctorAvailabilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService{
    private final appointmentRepository appointmentRepository;
    private final userService userService;
    private final DoctorAvailabilityService doctorAvailabilityService;

    @Transactional
    @Override
    public AppointmentResponse addAppointment(BookAppointmentRequest request) {

        // 1. Get doctor
        Doctor doctor = (Doctor) userService.getUserById(request.doctorId());

        // 2. Get current patient
        User currentUser = userService.getCurrentUser();

        // 4. Validate appointment time
        doctorAvailabilityService.validateAppointmentTime(
                doctor.getId(),
                request.date(),
                request.startTime(),
                request.endTime()
        );

        // 5. Create appointment
        Appointment appointment = new Appointment();

        appointment.setDoctor(doctor);
        appointment.setPatient(currentUser);
        appointment.setDate(request.date());
        appointment.setStartTime(request.startTime());
        appointment.setEndTime(request.endTime());
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointment.setReason(request.reason());
        appointment.setAppointmentNO("APT-"+ currentUser.getPhoneNumber());

        // 6. Save appointment
         appointmentRepository.save(appointment);

         return new AppointmentResponse(
                 appointment.getId(),
                 appointment.getAppointmentNO(),
                 doctor.getId(),
                 doctor.getFirstName(),
                 appointment.getDate(),
                 appointment.getStartTime(),
                 appointment.getEndTime(),
                 appointment.getStatus()
         );
    }


    @Override
    public Appointment updateAppointment(long appointmentId, AppointmentUpdateRequest request) {
        Appointment appointment = getAppointmentById(appointmentId);
        if (!Objects.equals(appointment.getStatus(), AppointmentStatus.BOOKED)) {
            throw new IllegalStateException("Appointment is not in pending state , Sorry this Appointment can't be updated");
        }
        appointment.setReason(request.getReason());
        appointment.setDate(request.getDate());
        appointment.setStartTime(request.getTime());
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
