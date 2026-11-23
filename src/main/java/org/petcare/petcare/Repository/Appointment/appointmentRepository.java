package org.petcare.petcare.Repository.Appointment;

import org.petcare.petcare.Enums.AppointmentStatus;
import org.petcare.petcare.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface appointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByAppointmentNO(String appointmentNO);
    List<Appointment> findByDoctor_IdAndDateAndStatusIn(
            Long doctorId,
            LocalDate date,
            Collection<AppointmentStatus> statuses
    );


    List<Appointment> findByPatient_IdAndDateAndStatusIn(
            Long patientId,
            LocalDate date,
            Collection<AppointmentStatus> statuses
    );
}
