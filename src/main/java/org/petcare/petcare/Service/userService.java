package org.petcare.petcare.Service;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Repository.userRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class userService {
    private final userRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
