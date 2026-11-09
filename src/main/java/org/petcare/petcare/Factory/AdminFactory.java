package org.petcare.petcare.Factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.petcare.petcare.Request.AdminRegistrationRequest;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Mapper.UserMapper;
import org.petcare.petcare.Models.Admin;
import org.petcare.petcare.Repository.adminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminFactory {
    private final UserMapper userMapper;
    private final adminRepository adminRepository;

    public Admin createAdmin(AdminRegistrationRequest request)
    {
        Admin admin = new Admin();
        userMapper.setCommonAttributes(request, admin);
        log.info(
                "Admin created with id: {}",
                admin.getId()

        );
        return adminRepository.save(admin);

    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
}
