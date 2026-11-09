package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.DTO.PatientDto;
import org.petcare.petcare.DTO.UserDto;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Patient;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Request.PatientRegistrationRequest;
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
@RequestMapping(urlMapping.PATIENT)
public class patientController {
    private final userService userService;
    private final UserMapper userMapper;


    @PostMapping()
    public ResponseEntity<ResponseAPI> saveUser(@RequestBody PatientRegistrationRequest req) {
        User user = userService.save(req);
        PatientDto userdto = userMapper.convertToPatientDto((Patient) user);
        return ResponseEntity.ok(new ResponseAPI("User saved successfully","Success" ,userdto));
    }

    @GetMapping()
    public ResponseEntity<ResponseAPI> getAllPatient() {
        List<Patient> patients= userService.getAllPatient();
        List<PatientDto>UsersDto= patients.stream().map(userMapper::convertToPatientDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseAPI("Users retrieved successfully", "Success", UsersDto));
    }


    @DeleteMapping(urlMapping.Delete)
    public ResponseEntity<ResponseAPI> delete(@PathVariable("id") long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(new ResponseAPI("User deleted successfully", "Success", null));

    }
}
