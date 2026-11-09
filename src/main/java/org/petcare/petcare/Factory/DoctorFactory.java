package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Request.DoctorRegistrationRequest;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Repository.doctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorFactory {
    private final UserMapper userMapper;
    private final doctorRepository doctorRepository;

    public Doctor createDoctor(DoctorRegistrationRequest request) {
        Doctor doctor = new Doctor();
        userMapper.setCommonAttributes(request, doctor);
        doctor.setSpecialization(request.getSpecialization());
        doctor.setBio(request.getBio());
        doctor.setExperience(request.getExperience());
        doctor.setConsultationFee(request.getConsultationFee());
        return doctorRepository.save(doctor);

    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }
}
