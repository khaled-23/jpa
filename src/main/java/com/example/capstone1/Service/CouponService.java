package com.example.capstone1.Service;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Coupon;
import com.example.capstone1.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final MerchantStockService merchantStockService;


    public List<Coupon> getCoupons() {
        return couponRepository.findAll();
    }

    public Boolean addCoupon(Coupon coupon, Integer merchantStockId) {
        boolean doesMerchantAndProductExists = merchantStockService.doesMerchantAndProductExists(merchantStockId);
        if(!doesMerchantAndProductExists){
            return false;
        }
        couponRepository.save(coupon);
        return true;
    }

    //checks if coupon is for the merchant
    public boolean isCouponValid(Integer couponId, Integer merchantStockId) {
        Coupon coupons = couponRepository.getById(couponId);
        if(coupons.getMerchantStockId()==merchantStockId&&coupons.getUses()>0){
            return true;
        }
        return false;
    }

    public int getCouponPercent(Integer id) {
        Coupon coupon = couponRepository.getById(id);
        return coupon.getPercent();
    }

    public double calculatePrice(double productPrice, double couponPercent) {
        return productPrice - productPrice * (couponPercent / 100);
    }

    public void reduceUses(Integer id) {
        Coupon coupon = couponRepository.getById(id);
        coupon.setUses(coupon.getUses()-1);
    }
}
