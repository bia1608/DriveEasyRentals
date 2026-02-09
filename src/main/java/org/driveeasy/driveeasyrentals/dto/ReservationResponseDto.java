package org.driveeasy.driveeasyrentals.dto;

import org.driveeasy.driveeasyrentals.model.ReservationStatus;

import java.time.LocalDate;

public class ReservationResponseDto {
    private Long id;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    public ReservationResponseDto() {}

    public ReservationResponseDto(Long id, Long carId, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
        this.id = id;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }
}
