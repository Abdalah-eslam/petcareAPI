package org.petcare.petcare.Repository.Appointment;

import org.petcare.petcare.Models.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface doctor_schedule_Repository extends JpaRepository<DoctorSchedule, Long> {
    public List<DoctorSchedule> findByDoctorId(Long doctorId);
    public List<DoctorSchedule> findByDoctorIdAndDayOfWeek(Long doctorId, DayOfWeek dayOfWeek);
}
