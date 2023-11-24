package com.assign.api.Service;

import com.assign.api.entity.Customer;

import java.util.List;

public interface CustService {
    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    boolean isDuplicateName(String firstName);

    Customer getCustomerById(Long id);

    Customer updateCustomer(Customer customer);

    void deleteCustomerById(Long id);

}
