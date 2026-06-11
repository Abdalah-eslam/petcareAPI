package org.petcare.petcare.Repository;

import org.petcare.petcare.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
}
