package org.petcare.petcare.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User{
    private Long id;
    private  String specialization;
    private BigDecimal ConsultationFee;
    private  String  Bio;
    private byte Experience;
}
