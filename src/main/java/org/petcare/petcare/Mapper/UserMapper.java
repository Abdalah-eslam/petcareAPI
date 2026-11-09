package org.petcare.petcare.Mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.petcare.petcare.DTO.*;
import org.petcare.petcare.Models.*;
import org.petcare.petcare.Request.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    // Set common attributes
    public void setCommonAttributes(RegistrationRequest request, User user) {
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
    }

    // Entity -> DTO

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public DoctorDto convertToDoctorDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDto.class);
    }

    public PatientDto convertToPatientDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }

    public AdminDto convertToAdminDto(Admin admin) {
        return modelMapper.map(admin, AdminDto.class);
    }

    // DTO -> Entity

    public User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public Doctor convertToDoctorEntity(DoctorDto doctorDto) {
        return modelMapper.map(doctorDto, Doctor.class);
    }

    public Patient convertToPatientEntity(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    public Admin convertToAdminEntity(AdminDto adminDto) {
        return modelMapper.map(adminDto, Admin.class);
    }
}