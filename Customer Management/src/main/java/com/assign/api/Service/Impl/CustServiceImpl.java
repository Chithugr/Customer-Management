package com.assign.api.Service.Impl;

import com.assign.api.Repository.CustRepo;
import com.assign.api.Service.CustService;
import com.assign.api.entity.Customer;
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
        return custrepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return custrepo.findById(String.valueOf(id)).get();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return custrepo.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        custrepo.deleteById(String.valueOf(id));
    }

}


