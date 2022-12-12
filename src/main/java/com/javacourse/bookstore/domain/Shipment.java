package com.javacourse.bookstore.domain;

import lombok.*;

import javax.persistence.*;

@Table(name="shipment")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="address")
    private String address;
}
