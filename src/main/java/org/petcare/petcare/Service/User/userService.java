package org.petcare.petcare.Service.User;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Exeption.ResourceNotFoundException;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Factory.UserFactroy;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.userRepository;
import org.petcare.petcare.Request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class userService implements IUserservice {
    private final userRepository userRepository;
    private final UserFactroy userFactroy;

    @Override
    public User save(RegistrationRequest req) {
        return userFactroy.createUser(req);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User update(Long userid, UserUpdateRequest req) {
        User user =getUserById(userid);

            // Update user fields with values from req
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setPhoneNumber(req.getPhoneNumber());
            user.setGender(req.getGender());
            user.setSpecialization(req.getSpecialization());

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
}
