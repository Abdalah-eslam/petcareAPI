package org.petcare.petcare;

import org.petcare.petcare.DTO.RegistrationRequest;
import org.petcare.petcare.Models.User;

public class UserMapper {
    public void SetComenAttribute(RegistrationRequest request, User user) {
        request.setId(user.getId());
        request.setEmail(user.getEmail());
        request.setPassword(user.getPassword());
        request.setFirstName(user.getFirstName());
        request.setLastName(user.getLastName());
        request.setPhoneNumber(user.getPhoneNumber());
        request.setGender(user.getGender());
        request.setSpecialization(user.getSpecialization());
        request.setUserType(user.getUserType());
        request.setEnabled(user.isEnabled());
    }
}
