package org.petcare.petcare.Controller;

import lombok.RequiredArgsConstructor;
import org.petcare.petcare.Models.User;
import org.petcare.petcare.Service.userService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class userController {
    private final userService userService;

    @PostMapping
    public User saveUser(User user) {
        return userService.saveUser(user);
    }
}
