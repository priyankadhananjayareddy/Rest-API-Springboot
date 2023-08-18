package com.ecomm.customer.app.repository;

import com.ecomm.customer.app.entity.CustomerDetailsForOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailsForOrderRepository extends JpaRepository<CustomerDetailsForOrder, Long> {

    @Query(value = "SELECT * FROM customer_details c WHERE c.customer_name=:customerName", nativeQuery = true)
    List<CustomerDetailsForOrder> getCustomerDetailsByName(String customerName);
}
