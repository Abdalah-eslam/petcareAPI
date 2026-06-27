package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Admin;
import org.petcare.petcare.Repository.adminRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminFactory {
    private final UserMapper userMapper;
    private final adminRepository adminRepository;

    public Admin createAdmin(RegistrationRequest request)
    {
        Admin admin = new Admin();
        userMapper.SetComenAttribute(request, admin);
        log.info(
                "Admin created with id: {}",
                admin.getId()

        );
        return adminRepository.save(admin);

    }
}
