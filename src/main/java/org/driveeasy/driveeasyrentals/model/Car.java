package org.driveeasy.driveeasyrentals.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    private CarCategory category;

    private BigDecimal dailyRate;

    private boolean underMaintenance;

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public Car setCategory(CarCategory category) {
        this.category = category;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CarCategory getCategory() {
        return category;
    }

    public Car setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
        return this;
    }

    public Car setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
        return this;
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
