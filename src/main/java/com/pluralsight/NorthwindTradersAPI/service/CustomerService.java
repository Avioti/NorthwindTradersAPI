package com.pluralsight.NorthwindTradersAPI.service;


import com.pluralsight.NorthwindTradersAPI.dao.CustomerDao;
import com.pluralsight.NorthwindTradersAPI.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void addCustomer(Customer customer) {
        customerDao.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAll();
    }

}
