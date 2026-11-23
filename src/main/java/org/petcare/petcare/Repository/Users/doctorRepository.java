package org.petcare.petcare.Repository.Users;

import org.petcare.petcare.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface doctorRepository extends JpaRepository<Doctor, Long> {
}

