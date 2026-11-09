package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Models.Appointment;
import org.petcare.petcare.Request.AppointmentUpdateRequest;
import org.petcare.petcare.Request.BookAppointmentRequest;
import org.petcare.petcare.Response.ResponseAPI;
import org.petcare.petcare.Service.appointment.AppointmentService;
import org.petcare.petcare.utils.urlMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(urlMapping.APPOINTMENT)
@RequiredArgsConstructor
public class appointmentController {
    private final AppointmentService appointmentService;


    @GetMapping()
    public ResponseEntity<ResponseAPI> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(new ResponseAPI("success", "success", appointments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> getAppointmentById(@PathVariable Long id) {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.ok(new ResponseAPI("success", "success", appointment));
    }

    @GetMapping("/appointment/{NO}")
    public ResponseEntity<ResponseAPI> getAppointmentByEmail(@PathVariable String NO) {
        Appointment appointment = appointmentService.getAppointmentsByNO(NO);
        return ResponseEntity.ok(new ResponseAPI("success", "success", appointment));
    }

    @PostMapping("/")
    public ResponseEntity<ResponseAPI> createAppointment(@RequestBody BookAppointmentRequest request , @RequestParam Long patId , @RequestParam Long docId) {

            Appointment savedAppointment = appointmentService.addAppointment(request,docId,patId);
            return ResponseEntity.ok(new ResponseAPI("success", "success", savedAppointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAPI> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateRequest UpdateRequest) {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, UpdateRequest);
            return ResponseEntity.ok(new ResponseAPI("success", "success", updatedAppointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAPI> deleteAppointment(@PathVariable Long id) {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok(new ResponseAPI("success", "success", null));

    }


}
