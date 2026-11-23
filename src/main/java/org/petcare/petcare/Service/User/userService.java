package org.petcare.petcare.Service.User;
import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
import org.petcare.petcare.Models.Admin;
import org.petcare.petcare.Models.Doctor;
import org.petcare.petcare.Models.Patient;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Factory.UserFactroy;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.Users.userRepository;
import org.petcare.petcare.Request.UserUpdateRequest;
import org.petcare.petcare.Security.AppUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class userService implements IUserservice , UserDetailsService {
    private final userRepository userRepository;
    private final UserFactroy userFactroy;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(RegistrationRequest req) {
        return userFactroy.createUser(req);
    }
    public List<Patient> getAllPatient() {
        return (List<Patient>) userFactroy.getUsers("Patient");
    }

    public List<Doctor> getAllDoctor() {
        return (List<Doctor>) userFactroy.getUsers("Doctor");
    }

    public List<Admin> getAllAdmin() {
        return (List<Admin>) userFactroy.getUsers("Admin");
    }


    @Override
    public User update(Long userid, UserUpdateRequest req) {
        User user =getUserById(userid);

            // Update user fields with values from req
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setPhoneNumber(req.getPhoneNumber());
            user.setGender(req.getGender());
        return userRepository.save(user);
    }
@Override
public User getUserById(Long userid) {
        return userRepository.findById(userid).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userid)
        );
    }
@Override
public void delete(Long userid) {
        userRepository.findById(userid).ifPresentOrElse(
                user -> userRepository.delete(user),
                () -> { throw new ResourceNotFoundException("User not found with id: " + userid); }
        )  ;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
        System.out.println("User found: " + user.getEmail() + ", Role: " + user.getClass().getSimpleName());
        return new AppUserDetails(user);


    }

    public User getCurrentUser() {


        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Jwt jwt = (Jwt) authentication.getCredentials();

        Long userId = Long.valueOf(jwt.getSubject());

        return getUserById(userId);
    }
}
