package org.petcare.petcare.Service.doctorSchedule;

import lombok.AllArgsConstructor;
import org.petcare.petcare.Enums.AppointmentStatus;
import org.petcare.petcare.Exeption.BadRequestInputException;
import org.petcare.petcare.Models.Appointment;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Models.DoctorSchedule;
import org.petcare.petcare.Repository.Appointment.appointmentRepository;
import org.petcare.petcare.Repository.Appointment.doctor_schedule_Repository;
import org.petcare.petcare.Response.Appointment.BookingResponse;
import org.petcare.petcare.Response.Appointment.DoctorDailyScheduleResponse;
import org.petcare.petcare.Response.Appointment.ShiftResponse;
import org.petcare.petcare.Service.User.userService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorAvailabilityService {

    private final doctor_schedule_Repository doctorScheduleRepository;
    private final appointmentRepository appointmentRepository;
    private final userService userService ;


    public DoctorDailyScheduleResponse getDoctorDailySchedule(
            Long doctorId,
            LocalDate date
    ) {
        if (userService.getUserById(doctorId).getClass() != Doctor.class ) {
           throw new BadRequestInputException("Invalid doctor ID");
        }
        if(date.isBefore(LocalDate.now())){
            throw new BadRequestInputException("Invalid date. Date cannot be in the past.");
        }
        // 1. Determine the day of the week
        DayOfWeek dayOfWeek = date.getDayOfWeek();


        // 2. Get doctor's schedules on this day
        List<DoctorSchedule> schedules =
                doctorScheduleRepository.findByDoctorIdAndDayOfWeek(
                        doctorId,
                        dayOfWeek
                );


        // 3. Get doctor's appointments on this date
        List<Appointment> appointments =
                appointmentRepository.findByDoctor_IdAndDateAndStatusIn(
                        doctorId,
                        date,
                        List.of(
                                AppointmentStatus.BOOKED,
                                AppointmentStatus.WAITING,
                                AppointmentStatus.CALLED,
                                AppointmentStatus.IN_PROGRESS
                        )
                );


        // 4. Sort schedules by start time
        List<DoctorSchedule> sortedSchedules =
                new ArrayList<>(schedules);

        sortedSchedules.sort(
                Comparator.comparing(DoctorSchedule::getStartTime)
        );


        // 5. Map schedules to ShiftResponse
        List<ShiftResponse> shifts = new ArrayList<>();

        for (DoctorSchedule schedule : sortedSchedules) {

            // Get appointments belonging to the current shift
            List<BookingResponse> bookings =
                    getShiftBookings(schedule, appointments);


            // Create shift response
            ShiftResponse shift = new ShiftResponse(
                    schedule.getStartTime(),
                    schedule.getEndTime(),
                    schedule.getSlotDuration(),
                    bookings
            );


            shifts.add(shift);
        }


        // 6. Return final response
        return new DoctorDailyScheduleResponse(
                doctorId,
                date,
                dayOfWeek,
                shifts
        );
    }


    // Get all bookings that overlap with the current shift
    private List<BookingResponse> getShiftBookings(
            DoctorSchedule schedule,
            List<Appointment> appointments
    ) {

        List<Appointment> shiftAppointments = new ArrayList<>();


        // 1. Filter appointments by time overlap
        for (Appointment appointment : appointments) {

            boolean overlaps =
                    appointment.getStartTime().isBefore(schedule.getEndTime())
                            &&
                            appointment.getEndTime().isAfter(schedule.getStartTime());

            if (overlaps) {
                shiftAppointments.add(appointment);
            }
        }


        // 2. Sort appointments by start time
        shiftAppointments.sort(
                Comparator.comparing(Appointment::getStartTime)
        );


        // 3. Convert Appointment entities to BookingResponse DTOs
        List<BookingResponse> bookings = new ArrayList<>();

        for (Appointment appointment : shiftAppointments) {

            BookingResponse booking = new BookingResponse(
                    appointment.getId(),
                    appointment.getStartTime(),
                    appointment.getEndTime(),
                    appointment.getStatus()
            );

            bookings.add(booking);
        }


        return bookings;
    }


    public void validateAppointmentTime(
            Long doctorId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    ) {

        // 1. Get doctor's schedules for the selected day
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<DoctorSchedule> schedules =
                doctorScheduleRepository.findByDoctorIdAndDayOfWeek(
                        doctorId,
                        dayOfWeek
                );

        // 2. Check if appointment is within doctor's working hours
        boolean isWithinWorkingHours = false;

        for (DoctorSchedule schedule : schedules) {

            boolean isInside =
                    !startTime.isBefore(schedule.getStartTime())
                            &&
                            !endTime.isAfter(schedule.getEndTime());

            if (isInside) {
                isWithinWorkingHours = true;
                break;
            }
        }

        if (!isWithinWorkingHours) {
            throw new BadRequestInputException(
                    "Appointment is outside doctor's working hours."
            );
        }

        // 3. Get doctor's existing appointments
        List<Appointment> appointments =
                appointmentRepository.findByDoctor_IdAndDateAndStatusIn(
                        doctorId,
                        date,
                        List.of(
                                AppointmentStatus.BOOKED,
                                AppointmentStatus.WAITING,
                                AppointmentStatus.CALLED,
                                AppointmentStatus.IN_PROGRESS
                        )
                );

        // 4. Check if the selected time overlaps with another appointment
        for (Appointment appointment : appointments) {

            boolean overlaps =
                    startTime.isBefore(appointment.getEndTime())
                            &&
                            endTime.isAfter(appointment.getStartTime());

            if (overlaps) {
                throw new BadRequestInputException(
                        "This time is already booked."
                );
            }
        }
    }
}