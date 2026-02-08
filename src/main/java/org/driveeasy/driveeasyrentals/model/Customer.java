package org.driveeasy.driveeasyrentals.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String documentNumber;

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


}
