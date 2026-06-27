package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Exeption.UserAlreadyExistsException;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.userRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MainUserFactory implements UserFactroy {
    private final userRepository userRepository;
    private final AdminFactory adminFactory;
    private final PatientFactory patientFactory;
    private final  DoctorFactory doctorFactory;

    @Override
    public User createUser(RegistrationRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        switch (request.getUserType()) {
            case "Admin":
                return adminFactory.createAdmin(request);
            case "Patient":
                return patientFactory.createPatient(request);
            case "Doctor":
                return doctorFactory.createDoctor(request);
            default:
                return null;
        }
    }
}


