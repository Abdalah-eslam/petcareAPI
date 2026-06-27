package org.petcare.petcare.Repository;

import org.petcare.petcare.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface patientRepository extends JpaRepository<Patient, Long> {
}
