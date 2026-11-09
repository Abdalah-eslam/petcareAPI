package org.petcare.petcare.Request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientRegistrationRequest extends RegistrationRequest {

    private LocalDate dateOfBirth;
    private String street;
    private String city;

}