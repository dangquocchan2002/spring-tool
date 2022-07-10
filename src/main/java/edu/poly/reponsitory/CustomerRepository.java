package edu.poly.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
