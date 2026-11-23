package org.petcare.petcare.Controller.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.coyote.BadRequestException;
import org.petcare.petcare.Models.DoctorSchedule;
import org.petcare.petcare.Request.CreateDoctorScheduleReq;
import org.petcare.petcare.Request.UpdateDoctorScheduleReq;
import org.petcare.petcare.Response.ResponseAPI;
import org.petcare.petcare.Service.doctorSchedule.doctorSchedule_Service;
import org.petcare.petcare.utils.urlMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(urlMapping.DOCTOR+"/schedules")
@AllArgsConstructor
public class doctorSchedule_Controller {
    private final doctorSchedule_Service doctorScheduleService;

    @GetMapping(urlMapping.GetOne)
    public ResponseEntity<ResponseAPI> getDoctorScheduleById(@PathVariable Long id) {
        DoctorSchedule doctorSchedule= doctorScheduleService.getDoctorScheduleById(id);
        return ResponseEntity.ok(new ResponseAPI("Doctor schedule retrieved successfully", "Success", doctorSchedule));
    }

    @PostMapping()
    public ResponseEntity<ResponseAPI> createDoctorSchedule(@RequestBody CreateDoctorScheduleReq req) {
           DoctorSchedule doctorSchedule= doctorScheduleService.createDoctorSchedule(req);
           return ResponseEntity.ok(new ResponseAPI("Doctor schedule created successfully", "Success", doctorSchedule));
    }

    @PutMapping(urlMapping.Update)
    public ResponseEntity<ResponseAPI> updateDoctorSchedule(@PathVariable Long id, @RequestBody UpdateDoctorScheduleReq req) {
        DoctorSchedule updatedSchedule = doctorScheduleService.updateDoctorSchedule(id, req);
        return ResponseEntity.ok(new ResponseAPI("Doctor schedule updated successfully", "Success", updatedSchedule));
    }

    @DeleteMapping(urlMapping.Delete)
    public ResponseEntity<ResponseAPI> deleteDoctorSchedule(@RequestParam Long id) {
        doctorScheduleService.deleteDoctorSchedule(id);
        return ResponseEntity.ok(new ResponseAPI("Doctor schedule deleted successfully", "Success", null));
    }



}
