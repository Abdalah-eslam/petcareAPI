package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.Models.Address;
import org.petcare.petcare.Request.PatientRegistrationRequest;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Patient;
import org.petcare.petcare.Repository.patientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientFactory  {
    private final UserMapper userMapper;
    private final patientRepository patientRepository;

    public Patient createPatient(PatientRegistrationRequest request) {
        Patient patient = new Patient();
        userMapper.setCommonAttributes(request, patient);
        Address address = new Address(request.getStreet(), request.getCity());
        patient.setAddress(address);
        log.info("Creating patient with email: {}", request.getEmail());
        log.warn("Creating patient with address: {}", patient.getAddress().getStreet());
        patient.setDateofBirth(request.getDateOfBirth());
        log.warn("Creating patient with BRD: {}", patient.getDateofBirth());
        return patientRepository.save(patient);
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }
}
