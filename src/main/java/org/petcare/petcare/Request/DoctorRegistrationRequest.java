package org.petcare.petcare.Request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRegistrationRequest extends RegistrationRequest {

    private String specialization;
    private BigDecimal consultationFee;
    private  String  bio;
    private byte experience;

}
