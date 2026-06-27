package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
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

    @PostMapping()
    public ResponseEntity<ResponseAPI> saveUser(@RequestBody RegistrationRequest req) {
        try {

            User user = userService.save(req);
            UserDto userdto = userMapper.convertToDto(user);
            return ResponseEntity.ok(new ResponseAPI("User saved successfully","Success" ,userdto));
        } catch (UserAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseAPI( ex.getMessage(), "Failed",null));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI( "Error saving user", "Failed",null));
        }
    }



    @GetMapping()
    public ResponseEntity<ResponseAPI> getAllUsers() {
        List<User> Users= userService.getAllUsers();
       List<UserDto>UsersDto= Users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
       return ResponseEntity.ok(new ResponseAPI("Users retrieved successfully", "Success", UsersDto));
    }



    @PutMapping(urlMapping.Update)
    public  ResponseEntity<ResponseAPI> Update ( @PathVariable("id") long userId , @RequestBody UserUpdateRequest req){
        try {
            User updatedUser = userService.update(userId, req);
            UserDto userdto = userMapper.convertToDto(updatedUser);
            return ResponseEntity.ok(new ResponseAPI("User updated successfully", "Success", userdto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseAPI(e.getMessage(), "Failed", null));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI("Error updating user", "Failed", null));
        }

    }
@GetMapping(urlMapping.GetOne)
    public ResponseEntity<ResponseAPI> getUserById(@PathVariable("id") long userId) {
        try {
            User user = userService.getUserById(userId);
            UserDto userdto = userMapper.convertToDto(user);
            return ResponseEntity.ok(new ResponseAPI("User retrieved successfully", "Success", userdto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseAPI(e.getMessage(), "Failed", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI("Error retrieving user", "Failed", null));
        }
    }

    @DeleteMapping(urlMapping.Delete)
    public ResponseEntity<ResponseAPI> delete(@PathVariable("id") long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.ok(new ResponseAPI("User deleted successfully", "Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseAPI(e.getMessage(), "Failed", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI("Error deleting user", "Failed", null));
        }
    }

}