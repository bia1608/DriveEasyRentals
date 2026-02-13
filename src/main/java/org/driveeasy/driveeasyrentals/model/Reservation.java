package org.driveeasy.driveeasyrentals.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
