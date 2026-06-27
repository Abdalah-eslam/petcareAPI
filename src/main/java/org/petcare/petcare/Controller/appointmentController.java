package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
import org.petcare.petcare.Models.Appointment;
import org.petcare.petcare.Request.AppointmentUpdateRequest;
import org.petcare.petcare.Response.ResponseAPI;
import org.petcare.petcare.Service.appointment.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class appointmentController {
    private final AppointmentService appointmentService;


    @GetMapping("/")
    public ResponseEntity<ResponseAPI> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(new ResponseAPI("success", "success", appointments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> getAppointmentById(@PathVariable Long id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.ok(new ResponseAPI("success", "success", appointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseAPI("Not Found", e.getMessage(), null));
        }
    }

    @GetMapping("/{NO}")
    public ResponseEntity<ResponseAPI> getAppointmentByEmail(@PathVariable String NO) {
        Appointment appointment = appointmentService.getAppointmentsByNO(NO);
        return ResponseEntity.ok(new ResponseAPI("success", "success", appointment));
    }

    @PostMapping("/")
    public ResponseEntity<ResponseAPI> createAppointment(@RequestBody Appointment appointment , @PathVariable Long patId , @PathVariable Long docId) {
        try {
            Appointment savedAppointment = appointmentService.addAppointment(appointment,docId,patId);
            return ResponseEntity.ok(new ResponseAPI("success", "success", savedAppointment));
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseAPI("Not Found", e.getMessage(), null));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAPI> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateRequest UpdateRequest) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, UpdateRequest);
            return ResponseEntity.ok(new ResponseAPI("success", "success", updatedAppointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseAPI("Not Found", e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAPI> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok(new ResponseAPI("success", "success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseAPI("Not Found", e.getMessage(), null));
        }
    }


}
