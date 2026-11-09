package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
import org.petcare.petcare.Models.Admin;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Models.Patient;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.DTO.UserDto;
import org.petcare.petcare.Exeption.UserAlreadyExistsException;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Request.UserUpdateRequest;
import org.petcare.petcare.Response.ResponseAPI;
import org.petcare.petcare.Service.User.userService;
import org.petcare.petcare.utils.urlMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(urlMapping.USER)
public class userController {
    private final userService userService;
    private final  UserMapper userMapper;

    @PutMapping(urlMapping.Update)
    public  ResponseEntity<ResponseAPI> Update ( @PathVariable("id") long userId , @RequestBody UserUpdateRequest req){
            User updatedUser = userService.update(userId, req);
            UserDto userdto = userMapper.convertToDto(updatedUser);
            return ResponseEntity.ok(new ResponseAPI("User updated successfully", "Success", userdto));

    }
@GetMapping(urlMapping.GetOne)
    public ResponseEntity<ResponseAPI> getUserById(@PathVariable("id") long userId) {
            User user = userService.getUserById(userId);
            UserDto userdto = userMapper.convertToDto(user);
            return ResponseEntity.ok(new ResponseAPI("User retrieved successfully", "Success", userdto));
    }


}