package org.driveeasy.driveeasyrentals.repository;

import org.driveeasy.driveeasyrentals.model.Reservation;
import org.driveeasy.driveeasyrentals.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        SELECT r FROM Reservation r
        WHERE r.car.id = :carId
          AND r.status = 'ACTIVE'
          AND r.startDate < :end
          AND :start < r.endDate
    """)
    List<Reservation> findConflicts(
            @Param("carId") Long carId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
}


