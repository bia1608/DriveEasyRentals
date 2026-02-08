package org.driveeasy.driveeasyrentals.service;

import org.driveeasy.driveeasyrentals.exception.NotFoundException;
import org.driveeasy.driveeasyrentals.model.Car;
import org.driveeasy.driveeasyrentals.model.CarCategory;
import org.driveeasy.driveeasyrentals.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updated) {
        Car existing = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found with id " + id));

        existing.setBrand(updated.getBrand());
        existing.setModel(updated.getModel());
        existing.setCategory((CarCategory) updated.getCategory());
        existing.setDailyRate(updated.getDailyRate());
        existing.setUnderMaintenance(updated.isUnderMaintenance());

        return carRepository.save(existing);
    }

    public List<Car> searchAvailable(CarCategory category,
                                     BigDecimal minRate,
                                     BigDecimal maxRate,
                                     LocalDate startDate,
                                     LocalDate endDate) {
        return carRepository.searchAvailable(category, minRate, maxRate, startDate, endDate);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}

