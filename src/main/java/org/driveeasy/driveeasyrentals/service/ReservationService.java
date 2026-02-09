package org.driveeasy.driveeasyrentals.service;

import org.driveeasy.driveeasyrentals.dto.ReservationResponseDto;
import org.driveeasy.driveeasyrentals.exception.ConflictException;
import org.driveeasy.driveeasyrentals.exception.InvalidDateException;
import org.driveeasy.driveeasyrentals.exception.NotFoundException;
import org.driveeasy.driveeasyrentals.model.Car;
import org.driveeasy.driveeasyrentals.model.Customer;
import org.driveeasy.driveeasyrentals.model.Reservation;
import org.driveeasy.driveeasyrentals.model.ReservationStatus;
import org.driveeasy.driveeasyrentals.repository.CarRepository;
import org.driveeasy.driveeasyrentals.repository.CustomerRepository;
import org.driveeasy.driveeasyrentals.repository.ReservationRepository;
import org.driveeasy.driveeasyrentals.util.DateUtils;
import org.driveeasy.driveeasyrentals.util.PriceCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              CarRepository carRepository,
                              CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public Reservation book(Long carId, Long customerId,
                            LocalDate start, LocalDate end) {

        if (!start.isBefore(end)) {
            throw new InvalidDateException("Invalid date range");
        }

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car not found"));

        if (car.isUnderMaintenance()) {
            throw new ConflictException("Car under maintenance");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        boolean conflict = !reservationRepository
                .findConflicts(carId, start, end)
                .isEmpty();

        if (conflict) {
            throw new ConflictException("Car already booked");
        }

        BigDecimal total = PriceCalculator.calculateTotal(start, end, car.getDailyRate());

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setCustomer(customer);
        reservation.setStartDate(start);
        reservation.setEndDate(end);
        reservation.setTotalCost(total);
        reservation.setStatus(ReservationStatus.ACTIVE);

        return reservationRepository.save(reservation);
    }

    public boolean isCarAvailable(Long carId, String startDate, String endDate) {
        LocalDate start = DateUtils.parseDate(startDate);
        LocalDate end = DateUtils.parseDate(endDate);

        if (!start.isBefore(end)) {
            throw new InvalidDateException("Invalid date range");
        }

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car not found"));

        if (car.isUnderMaintenance()) {
            return false;
        }

        return reservationRepository.findConflicts(carId, start, end).isEmpty();
    }

    public List<ReservationResponseDto> findActiveByCar(Long carId) {
        // Query for active reservations overlapping a wide range; reuse repository query by scanning near future
        List<Reservation> reservations = reservationRepository.findAll()
                .stream()
                .filter(r -> r.getCar() != null && carId.equals(r.getCar().getId()))
                .collect(Collectors.toList());

        return reservations.stream()
                .map(r -> new ReservationResponseDto(
                        r.getId(),
                        r.getCar() != null ? r.getCar().getId() : null,
                        r.getStartDate(),
                        r.getEndDate(),
                        r.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
