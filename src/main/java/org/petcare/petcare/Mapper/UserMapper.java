package org.petcare.petcare.Mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.DTO.UserDto;
import org.petcare.petcare.Models.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;



    public void SetComenAttribute(RegistrationRequest request, User user) {
        user.setId(request.getId());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        user.setUserType(request.getUserType());
        user.setEnabled(request.isEnabled());
    }
    public  UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
    public  User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
