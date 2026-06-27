package org.petcare.petcare.Service.User;

import org.petcare.petcare.Models.User;
import org.petcare.petcare.Request.RegistrationRequest;
import org.petcare.petcare.Request.UserUpdateRequest;

public interface IUserservice {
    User save(RegistrationRequest req);

    User update(Long Userid, UserUpdateRequest req);

    User getUserById(Long userid);

    void delete(Long userid);
}
