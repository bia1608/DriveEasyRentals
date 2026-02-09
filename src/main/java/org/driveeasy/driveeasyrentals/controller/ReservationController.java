package org.driveeasy.driveeasyrentals.controller;

import org.driveeasy.driveeasyrentals.dto.ReservationResponseDto;
import org.driveeasy.driveeasyrentals.service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/check")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public boolean checkAvailability(Long carId, String startDate, String endDate) {
        return reservationService.isCarAvailable(carId, startDate, endDate);
    }

    @GetMapping("/car/{carId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','PARTNER')")
    public List<ReservationResponseDto> listByCar(@PathVariable Long carId) {
        return reservationService.findActiveByCar(carId);
    }
}
