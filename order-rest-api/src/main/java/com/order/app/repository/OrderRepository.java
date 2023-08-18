package com.order.app.repository;

import com.order.app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from ecomm_order.order o where transcation_id = (select max(transcation_id) from ecomm_order.order);", nativeQuery = true)
    Order getOrderDetailsByMaxTransactionId();
}
