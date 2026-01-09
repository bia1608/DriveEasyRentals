package com.driveeasy.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {

    private Long id;
    private String brand;
    private String model;
    private CarCategory category;
    private BigDecimal dailyRate;
    private boolean underMaintenance;

    public Car() {
    }

    public Car(Long id,
               String brand,
               String model,
               CarCategory category,
               BigDecimal dailyRate,
               boolean underMaintenance) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.dailyRate = dailyRate;
        this.underMaintenance = underMaintenance;
    }

    public Long getId() {
        return id;
    }

    public Car setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public CarCategory getCategory() {
        return category;
    }

    public Car setCategory(CarCategory category) {
        this.category = category;
        return this;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public Car setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
        return this;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public Car setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", category=" + category +
                ", dailyRate=" + dailyRate +
                ", underMaintenance=" + underMaintenance +
                '}';
    }
}
