package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.DTO.AdminDto;
import org.petcare.petcare.DTO.UserDto;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Admin;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Request.AdminRegistrationRequest;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Response.ResponseAPI;
import org.petcare.petcare.Service.User.userService;
import org.petcare.petcare.utils.urlMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(urlMapping.ADMIN)
public class adminController {
    private final userService userService;
    private final UserMapper userMapper;


    @PostMapping()
    public ResponseEntity<ResponseAPI> saveUser(@RequestBody AdminRegistrationRequest req) {
        User user = userService.save(req);
        AdminDto userdto = userMapper.convertToAdminDto((Admin) user);
        return ResponseEntity.ok(new ResponseAPI("User saved successfully","Success" ,userdto));
    }

    @GetMapping()
    public ResponseEntity<ResponseAPI> getAllAdmin() {
        List<Admin> admins= userService.getAllAdmin();
        List<AdminDto>UsersDto= admins.stream().map(userMapper::convertToAdminDto).collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseAPI("Users retrieved successfully", "Success", UsersDto));
    }

    @DeleteMapping(urlMapping.Delete)
    public ResponseEntity<ResponseAPI> delete(@PathVariable("id") long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(new ResponseAPI("User deleted successfully", "Success", null));

    }
}
