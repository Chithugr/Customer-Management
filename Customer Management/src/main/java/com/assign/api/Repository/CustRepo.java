package com.assign.api.Repository;

import com.assign.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustRepo extends JpaRepository<Customer, String> {
}
