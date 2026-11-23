package org.petcare.petcare.Service.doctorSchedule;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.petcare.petcare.Exeption.BadRequestInputException;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Models.DoctorSchedule;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.Appointment.doctor_schedule_Repository;
import org.petcare.petcare.Request.CreateDoctorScheduleReq;
import org.petcare.petcare.Request.UpdateDoctorScheduleReq;
import org.petcare.petcare.Service.User.userService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@AllArgsConstructor
public class doctorSchedule_Service implements iDoctorSchedule {
    final doctor_schedule_Repository doctorScheduleRepository;
    final userService userService;

    @Override
    public DoctorSchedule createDoctorSchedule(CreateDoctorScheduleReq req) throws BadRequestInputException {
        User user = userService.getUserById(req.doctorId());
        if (!(user instanceof Doctor doctor)) {
            throw new BadRequestInputException("User is not a doctor");
        }
        DoctorSchedule doctorSchedule = new DoctorSchedule();
       doctorSchedule.setEndTime(req.endTime());
       doctorSchedule.setStartTime(req.startTime());
       doctorSchedule.setDayOfWeek(req.dayOfWeek());
       doctorSchedule.setSlotDuration(req.slotDuration());

       doctorSchedule.setDoctor(doctor);

       return doctorScheduleRepository.save(doctorSchedule);
    }

    @Override
    public DoctorSchedule getDoctorScheduleById(Long id) {
        return doctorScheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DoctorSchedule with id " + id + " not found"));
    }

    @Override
    public DoctorSchedule updateDoctorSchedule(Long id, UpdateDoctorScheduleReq req) {
        DoctorSchedule existingSchedule =
                doctorScheduleRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "DoctorSchedule with id " + id + " not found"
                                ));

        existingSchedule.setStartTime(req.startTime());
        existingSchedule.setEndTime(req.endTime());
        existingSchedule.setDayOfWeek(req.dayOfWeek());
        existingSchedule.setSlotDuration(req.slotDuration());

       return doctorScheduleRepository.save(existingSchedule);

    }

    @Override
    public void deleteDoctorSchedule(Long id) {
        DoctorSchedule schedule =
                doctorScheduleRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "DoctorSchedule with id " + id + " not found"
                                ));

        doctorScheduleRepository.delete(schedule);
    }

    @Override
    public List<DoctorSchedule> getAllDoctorSchedules(Long doctorId) {
        if (doctorId == null) {
            return doctorScheduleRepository.findAll();
        }
     return    doctorScheduleRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<DoctorSchedule> getDoctorScheduleByDayOfWeek(Long doctorId, DayOfWeek dayOfWeek) {
      return   doctorScheduleRepository.findByDoctorIdAndDayOfWeek(doctorId, dayOfWeek);
    }

}
