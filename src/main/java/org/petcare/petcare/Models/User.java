package org.petcare.petcare.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" , "enabled", "appointments"})
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(
            name = "person_seq",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    private  Long id;
    private  String firstName;
    private String lastName;
    private String gender;
    @Column(name = "mobile")
    private  String phoneNumber;
    @Column(unique = true)
    private  String email;
    private  String password;
    @ColumnDefault("True")
    private  boolean isEnabled =true ;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Transient
    private List<Appointment> appointments;


}
