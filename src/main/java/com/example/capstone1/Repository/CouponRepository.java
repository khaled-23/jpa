package com.example.capstone1.Repository;

import com.example.capstone1.Model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
}
