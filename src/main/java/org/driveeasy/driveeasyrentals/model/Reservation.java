package com.driveeasy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public enum ReservationStatus {
    ACTIVE,
    CANCELLED,
    COMPLETED
}

public class Reservation {

    private Long id;
    private Long carId;
    private Long customerId;
    private LocalDate startDate;
    private LocalDate endDate; // exclusive
    private BigDecimal totalCost;
    private ReservationStatus status;

    public Reservation() {
    }

    public Reservation(Long id,
                       Long carId,
                       Long customerId,
                       LocalDate startDate,
                       LocalDate endDate,
                       BigDecimal totalCost,
                       ReservationStatus status) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Reservation setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public Reservation setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Reservation setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Reservation setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Reservation setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public Reservation setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public Reservation setStatus(ReservationStatus status) {
        this.status = status;
        return this;
    }

    public boolean isActive() {
        return ReservationStatus.ACTIVE.equals(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", carId=" + carId +
                ", customerId=" + customerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                ", status=" + status +
                '}';
    }
}
