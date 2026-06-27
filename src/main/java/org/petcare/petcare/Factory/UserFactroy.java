package org.petcare.petcare.Factory;

import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Models.User;

public interface UserFactroy {
    User createUser(RegistrationRequest request );
}
