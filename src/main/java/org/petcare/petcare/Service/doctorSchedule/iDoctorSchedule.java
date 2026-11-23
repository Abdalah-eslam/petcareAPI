package org.petcare.petcare.Service.doctorSchedule;

import org.apache.coyote.BadRequestException;
import org.petcare.petcare.Models.DoctorSchedule;
import org.petcare.petcare.Request.CreateDoctorScheduleReq;
import org.petcare.petcare.Request.UpdateDoctorScheduleReq;

import java.time.DayOfWeek;
import java.util.List;

public interface iDoctorSchedule {
    public DoctorSchedule createDoctorSchedule(CreateDoctorScheduleReq req) throws BadRequestException;
    public DoctorSchedule getDoctorScheduleById(Long id);
    public DoctorSchedule updateDoctorSchedule(Long id, UpdateDoctorScheduleReq req);
    public void deleteDoctorSchedule(Long id);
    public List<DoctorSchedule> getAllDoctorSchedules(Long doctorId);
    public List<DoctorSchedule> getDoctorScheduleByDayOfWeek(Long doctorId, DayOfWeek dayOfWeek);

}
