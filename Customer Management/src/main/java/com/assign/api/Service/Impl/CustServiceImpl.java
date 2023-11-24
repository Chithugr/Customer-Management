package com.assign.api.Service.Impl;

import com.assign.api.Repository.CustRepo;
import com.assign.api.Service.CustService;
import com.assign.api.entity.Customer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustServiceImpl implements CustService {

    private CustRepo custrepo;

    public CustServiceImpl(CustRepo custrepo){
        super();
        this.custrepo=custrepo;
    }
    @Override
    public List<Customer> getAllCustomers() {
        return custrepo.findAll();
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        try {
            if (isDuplicateName(customer.getFirst_name())) {
                throw new DuplicateName("Duplicate customer name: " + customer.getFirst_name());
            } else {
                return custrepo.save(customer);
            }
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateName("Duplicate customer name: " + customer.getFirst_name());
        }
    }


//    @Override
//    public Customer saveCustomer(Customer customer) {
//        if (isDuplicateName(customer.getFirst_name())) {
//            throw new DuplicateName("Duplicate customer name: " + customer.getFirst_name());
//        }else {
//            return custrepo.save(customer);
//        }
//    }

    @Override
    public boolean isDuplicateName(String firstName) {
        for (Customer customer : custrepo.findAll()) {
            if (customer.getFirst_name().equals(firstName)) {
                return true; // Duplicate name found
            }
        }
        return false;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return custrepo.findById(id).get();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return custrepo.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        custrepo.deleteById(id);
    }


}


