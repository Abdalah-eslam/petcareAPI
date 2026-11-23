package org.petcare.petcare.Repository.Users;

import org.petcare.petcare.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<Admin, Long> {
}
