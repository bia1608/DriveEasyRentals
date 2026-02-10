package org.driveeasy.driveeasyrentals.service;

import org.driveeasy.driveeasyrentals.exception.ValidationException;
import org.driveeasy.driveeasyrentals.model.Customer;
import org.driveeasy.driveeasyrentals.repository.CustomerRepository;
import org.driveeasy.driveeasyrentals.util.InputValidator;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(Customer customer) {
        InputValidator.validateEmail(customer.getEmail());
        InputValidator.validatePhone(customer.getPhone());

        customerRepository.findByEmail(customer.getEmail())
                .ifPresent(c -> {
                    throw new ValidationException("Email already in use: " + customer.getEmail());
                });

        customerRepository.findByPhone(customer.getPhone())
                .ifPresent(c -> {
                    throw new ValidationException("Phone already in use: " + customer.getPhone());
                });

        return customerRepository.save(customer);
    }
}

