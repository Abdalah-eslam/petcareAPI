package org.petcare.petcare.Controller.Users;

import org.petcare.petcare.Response.LoginResponse;
import org.petcare.petcare.Security.AppUserDetails;
import org.petcare.petcare.Security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.DTO.UserDto;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Request.LoginRequest;
import org.petcare.petcare.Request.UserUpdateRequest;
import org.petcare.petcare.Response.ResponseAPI;
import org.petcare.petcare.Service.User.userService;
import org.petcare.petcare.utils.urlMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(urlMapping.USER)
public class userController {
    private final userService userService;
    private final  UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );
        AppUserDetails userDetails =
                (AppUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse(token, "Bearer", userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getAuthorities()
        );

        return ResponseEntity.ok(new ResponseAPI("Login successfully","Success", loginResponse ));
    }



}