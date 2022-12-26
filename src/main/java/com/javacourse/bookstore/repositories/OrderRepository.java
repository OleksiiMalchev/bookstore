package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

//    @Query(value = "SELECT * FROM orders JOIN customer ON customer.id = orders.customer_id Where customer_id = 10",
//            nativeQuery = true)
    @Query("SELECT o FROM Order o JOIN FETCH o.customer c WHERE c.id = :id")
    List<Order> getAllOrderByCustomerId(@Param("id") Long idCustomer);

    @Query(value = "SELECT * FROM orders JOIN order_status ON orders.id = order_status.order_id " +
            "Where order_status.name_status = :statusName " , nativeQuery = true)
    List<Order> getAllOrderByStatus(@Param("statusName") String statusName);
    @Query(value = "SELECT * FROM orders JOIN order_status ON orders.id = order_status.order_id " +
            "Where order_status.name_status = :statusName and customer_id = :id", nativeQuery = true)
    Optional<Order> getOrderByCustomerIdAndStatus(@Param("id") Long idCustomer, @Param("statusName") String statusName);
}