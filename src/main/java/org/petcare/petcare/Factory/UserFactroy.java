package org.petcare.petcare.Factory;

import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Models.User;

import java.util.List;

public interface UserFactroy {
    User createUser(RegistrationRequest request );
    List<? extends User>  getUsers (String userType);
}
