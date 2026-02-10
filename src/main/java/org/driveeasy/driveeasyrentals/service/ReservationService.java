package org.driveeasy.driveeasyrentals.service;

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
}
