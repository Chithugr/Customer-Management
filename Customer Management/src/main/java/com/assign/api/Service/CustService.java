package com.assign.api.Service;

import com.assign.api.Repository.CustRepo;

import com.assign.api.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class CustService {
    @Autowired
    private CustRepo repo;

    public void addCust(Customer c) {
        repo.save(c);
    }

    public List<Customer> getAllCust() {
        return repo.findAll();
    }

    public Customer getCustById(int id) {
        Optional<Customer> c = repo.findById(String.valueOf(id));
        if (c.isPresent()) {
            return c.get();
        }
        return null;
    }

    public void deleteCust(int id) {
        repo.deleteById(String.valueOf(id));
    }


}
