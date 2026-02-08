package org.driveeasy.driveeasyrentals;

import org.driveeasy.driveeasyrentals.console.MenuHandler;
import org.driveeasy.driveeasyrentals.service.CarService;
import org.driveeasy.driveeasyrentals.service.CustomerService;
import org.driveeasy.driveeasyrentals.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DriveEasyRentalsApplication implements CommandLineRunner {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReservationService reservationService;
    public static void main(String[] args) {
        SpringApplication.run(DriveEasyRentalsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        new MenuHandler(carService, customerService, reservationService).start();
    }

}
