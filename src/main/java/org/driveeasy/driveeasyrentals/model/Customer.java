package com.driveeasy.model;

import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String documentNumber;

    public Customer() {
    }

    public Customer(Long id,
                    String name,
                    String email,
                    String phone,
                    String documentNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.documentNumber = documentNumber;
    }

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Customer setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}
