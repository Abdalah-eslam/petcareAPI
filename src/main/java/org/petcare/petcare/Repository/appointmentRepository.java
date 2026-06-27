package org.petcare.petcare.Repository;

import org.petcare.petcare.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface appointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByAppointmentNO(String appointmentNO);
}
