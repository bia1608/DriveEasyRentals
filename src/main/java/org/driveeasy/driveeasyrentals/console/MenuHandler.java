package org.driveeasy.driveeasyrentals.console;

import org.driveeasy.driveeasyrentals.model.Car;
import org.driveeasy.driveeasyrentals.model.CarCategory;
import org.driveeasy.driveeasyrentals.service.CarService;
import org.driveeasy.driveeasyrentals.service.CustomerService;
import org.driveeasy.driveeasyrentals.service.ReservationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuHandler {

    private final CarService carService;
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private final Scanner scanner = new Scanner(System.in);

    public MenuHandler(CarService carService, CustomerService customerService, ReservationService reservationService) {
        this.carService = carService;
        this.customerService = customerService;
        this.reservationService = reservationService;
    }


    public void start() {
        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addCarFlow();
                case "2" -> listCars();
                case "0" -> running = false;
                default -> System.out.println("Unknown option");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("=== DriveEasy Rentals ===");
        System.out.println("1. Add car");
        System.out.println("2. List cars");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private void addCarFlow() {
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Category (SUV, SEDAN, HATCHBACK, LUXURY, VAN, TRUCK): ");
        String categoryStr = scanner.nextLine();
        System.out.print("Daily rate: ");
        BigDecimal rate = new BigDecimal(scanner.nextLine());

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setCategory(CarCategory.valueOf(categoryStr.toUpperCase()));
        car.setDailyRate(rate);
        car.setUnderMaintenance(false);

        carService.addCar(car);
        System.out.println("Car added: " + car);
    }

    private void listCars() {
        carService.findAll().forEach(System.out::println);
    }
}
