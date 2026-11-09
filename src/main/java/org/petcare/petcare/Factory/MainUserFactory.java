package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Models.Admin;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Models.Patient;
import org.petcare.petcare.Request.AdminRegistrationRequest;
import org.petcare.petcare.Request.DoctorRegistrationRequest;
import org.petcare.petcare.Request.PatientRegistrationRequest;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Exeption.UserAlreadyExistsException;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.userRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class MainUserFactory implements UserFactroy {
    private final userRepository userRepository;
    private final AdminFactory adminFactory;
    private final PatientFactory patientFactory;
    private final DoctorFactory doctorFactory;


    public Doctor createDoctor(DoctorRegistrationRequest request) {

        checkEmail(request.getEmail());

        return doctorFactory.createDoctor(request);
    }

    public Patient createPatient(PatientRegistrationRequest request) {

        checkEmail(request.getEmail());

        return patientFactory.createPatient(request);
    }

    public Admin createAdmin(AdminRegistrationRequest request) {

        checkEmail(request.getEmail());

        return adminFactory.createAdmin(request);
    }

    private void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User already exists");
        }
    }


    @Override
    public User createUser(RegistrationRequest request) {
        switch (request.getClass().getSimpleName()) {
            case "AdminRegistrationRequest":
                return createAdmin((AdminRegistrationRequest) request);
            case "PatientRegistrationRequest":
                return createPatient((PatientRegistrationRequest) request);
            case "DoctorRegistrationRequest":
                return createDoctor((DoctorRegistrationRequest) request);
            default:
                throw new IllegalArgumentException("Invalid registration request type");
        }
    }

    @Override
    public List<? extends User> getUsers(String userType) {
        switch (userType) {
            case "Admin":
                return adminFactory.getAdmins();
            case "Patient":
                return patientFactory.getPatients();
            case "Doctor":
                return doctorFactory.getDoctors();
            default:
                return  null ;
        }


    }
}


