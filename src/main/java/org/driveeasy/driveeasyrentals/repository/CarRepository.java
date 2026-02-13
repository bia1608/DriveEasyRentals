package org.driveeasy.driveeasyrentals.repository;

import org.driveeasy.driveeasyrentals.model.Car;
import org.driveeasy.driveeasyrentals.model.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByCategory(CarCategory category);

    @Query("""
        SELECT c FROM Car c
        WHERE c.underMaintenance = false
          AND c.dailyRate BETWEEN :min AND :max
    """)
    List<Car> searchAvailable(
            @Param("min") BigDecimal min,
            @Param("max") BigDecimal max
    );


        @Query("""
        SELECT c FROM Car c
        WHERE (:category IS NULL OR c.category = :category)
          AND (:minRate IS NULL OR c.dailyRate >= :minRate)
          AND (:maxRate IS NULL OR c.dailyRate <= :maxRate)
          AND c.underMaintenance = false
          AND NOT EXISTS (
                        SELECT r FROM Reservation r
                        WHERE r.car = c
                          AND r.status = 'ACTIVE'
                          AND r.startDate < :endDate
                          AND :startDate < r.endDate
                    )
    """)
        List<Car> searchAvailable(
                @Param("category") CarCategory category,
                @Param("minRate") BigDecimal minRate,
                @Param("maxRate") BigDecimal maxRate,
                @Param("startDate") LocalDate startDate,
                @Param("endDate") LocalDate endDate);
    }

