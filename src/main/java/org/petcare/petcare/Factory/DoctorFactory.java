package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Repository.doctorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorFactory {
    private final UserMapper userMapper;
    private final doctorRepository doctorRepository;

    public Doctor createDoctor(RegistrationRequest request) {
        Doctor doctor = new Doctor();
        userMapper.SetComenAttribute(request, doctor);
        doctor.setSpecialization(request.getSpecialization());
        return doctorRepository.save(doctor);

    }
}
