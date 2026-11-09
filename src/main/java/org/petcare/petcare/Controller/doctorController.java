package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.DTO.DoctorDto;
import org.petcare.petcare.DTO.UserDto;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Request.DoctorRegistrationRequest;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Response.ResponseAPI;
import org.petcare.petcare.Service.User.userService;
import org.petcare.petcare.utils.urlMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(urlMapping.DOCTOR)
public class doctorController {
    private final userService userService;
    private final UserMapper userMapper;

        @PostMapping()
        public ResponseEntity<ResponseAPI> saveUser(@RequestBody DoctorRegistrationRequest req) {
            User user = userService.save(req);
            DoctorDto userdto = userMapper.convertToDoctorDto((Doctor) user);
            return ResponseEntity.ok(new ResponseAPI("User saved successfully","Success" ,userdto));
        }

    @GetMapping()
    public ResponseEntity<ResponseAPI> getAllDoctor() {
        List<Doctor> doctors= userService.getAllDoctor();
        List<DoctorDto>UsersDto= doctors.stream().map(userMapper::convertToDoctorDto).collect(Collectors.toList());
        System.out.println(doctors.get(0).getSpecialization());
            System.out.println(UsersDto.get(0).getSpecialization());
        return ResponseEntity.ok(new ResponseAPI("Users retrieved successfully", "Success", UsersDto));
    }

    @DeleteMapping(urlMapping.Delete)
    public ResponseEntity<ResponseAPI> delete(@PathVariable("id") long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(new ResponseAPI("User deleted successfully", "Success", null));

    }
}
