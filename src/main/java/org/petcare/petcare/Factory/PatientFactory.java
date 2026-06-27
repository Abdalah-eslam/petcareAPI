package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Patient;
import org.petcare.petcare.Repository.patientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFactory  {
    private final UserMapper userMapper;
    private final patientRepository patientRepository;

    public Patient createPatient(RegistrationRequest request) {
        Patient patient = new Patient();
        userMapper.SetComenAttribute(request, patient);
        return patientRepository.save(patient);
    }
}
