package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Coupon;
import com.example.capstone1.Service.CouponService;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/add/{merchantSockId}")
    public ResponseEntity addCoupon(@PathVariable Integer merchantStockId
            ,@RequestBody @Valid Coupon coupon, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isAdded = couponService.addCoupon(coupon, merchantStockId);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("coupon added"));
        }else return ResponseEntity.status(400).body(new ApiResponse("could not add coupon"));

    }
    @GetMapping("/coupons")
    public ResponseEntity getCoupons(){
        return ResponseEntity.status(200).body(couponService.getCoupons());
    }
}
