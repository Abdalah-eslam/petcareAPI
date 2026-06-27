package org.petcare.petcare.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.petcare.petcare.Enums.AppointmentStatus;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String reason ;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne(fetch = FetchType.LAZY)
    private User doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    private User patient;
    private String  appointmentNO;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;


    public void Addpatient(User patient) {
        this.setPatient(patient);
        if (patient.getAppointments() == null) {
            patient.setAppointments(new java.util.ArrayList<>());
        }
        patient.getAppointments().add(this);
    }

    public void AddDoctor(User doctor) {
        this.setDoctor(doctor);
        if (doctor.getAppointments() == null) {
            doctor.setAppointments(new java.util.ArrayList<>());
        }
        doctor.getAppointments().add(this);
    }

}
