package com.javacourse.bookstore.mappers.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "user")
@Getter
@Setter
//@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="phone_number")
    private Long phoneNumber;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="nick_name")
    private String nickName;
    @Column(name="email")
    private String email;
    @Column(name="age")
    private Integer age;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
}
