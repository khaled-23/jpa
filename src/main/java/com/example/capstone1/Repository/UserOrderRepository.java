package com.example.capstone1.Repository;

import com.example.capstone1.Model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {
}
