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

    public Reservation cancel(Long reservationId, Long customerId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found with id: " + reservationId));

        // Verifica ca rezervarea apartine clientului (securitate)
        if (!reservation.getCustomer().getId().equals(customerId)) {
            throw new ConflictException("Reservation does not belong to this customer");
        }

        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new ConflictException("Only ACTIVE reservations can be cancelled");
        }

        // Regula: anularea e permisa doar daca nu a inceput inca
        if (!LocalDate.now().isBefore(reservation.getStartDate())) {
            throw new ConflictException("Cannot cancel a reservation that has already started");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        return reservationRepository.save(reservation);
    }

    /**
     * Returneaza toate rezervarile unui client (istoric personal).
     * Foloseste Stream pentru a sorta dupa data de start descrescator.
     */
    public List<Reservation> findByCustomer(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + customerId));

        return reservationRepository.findByCustomerId(customerId)
                .stream()
                .sorted((r1, r2) -> r2.getStartDate().compareTo(r1.getStartDate()))
                .toList();
    }

    /**
     * Returneaza toate rezervarile (pentru admin).
     */
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
