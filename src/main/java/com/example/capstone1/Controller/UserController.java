package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Model.UserOrder;
import com.example.capstone1.Service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
            return ResponseEntity.status(200).body(new ApiResponse("user added"));

    }

    @GetMapping("/users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        Boolean isUpdated = userService.updateUser(id,user);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("user updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeUser(@PathVariable Integer id){
        Boolean isRemoved = userService.removeUser(id);
        if(isRemoved){
            return ResponseEntity.status(200).body("user removed: "+id);
        }else return  ResponseEntity.status(400).body("user not found");
    }

    @PostMapping("/buy/{userId}/{merchantStockId}/{merchantId}/{productId}")
    public ResponseEntity buyProduct(@PathVariable Integer userId, @PathVariable Integer merchantStockId,@PathVariable Integer merchantId, @PathVariable Integer productId){
        String message = userService.buyProduct(userId,merchantStockId,merchantId,productId);
        return switch (message) {
            case "user does not exists" -> ResponseEntity.status(400).body(new ApiResponse("user not found"));
            case "merchantStock does not exists" ->
                    ResponseEntity.status(400).body(new ApiResponse("merchant or product does not exists"));
            case "merchant does not have stock" ->
                    ResponseEntity.status(400).body(new ApiResponse("merchant does not have stock"));
            case "user does not have enough credit" ->
                    ResponseEntity.status(400).body(new ApiResponse("user does not have enough credit"));
            default -> ResponseEntity.status(200).body(new ApiResponse(message));
        };
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity getOrders(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(userService.getOrderHistory(userId));
    }

    @PostMapping("/buy/{userId}/{merchantStockId}/{merchantId}/{productId}/{couponId}")
    public ResponseEntity buyWithCoupon(@PathVariable Integer userId, @PathVariable Integer merchantStockId,
                                        @PathVariable Integer merchantId, @PathVariable Integer productId,@PathVariable Integer couponId){
        String message = userService.buyWithCoupon(userId,merchantStockId,merchantId,productId,couponId);
        return switch (message) {
            case "user does not exists" -> ResponseEntity.status(400).body(new ApiResponse("user not found"));
            case "merchantStock does not exists" ->
                    ResponseEntity.status(400).body(new ApiResponse("merchant or product does not exists"));
            case "merchant does not have stock" ->
                    ResponseEntity.status(400).body(new ApiResponse("merchant does not have stock"));
            case "user does not have enough credit" ->
                    ResponseEntity.status(400).body(new ApiResponse("user does not have enough credit"));
            case "coupon is invalid" -> ResponseEntity.status(400).body(new ApiResponse("coupon is invalid"));
            default -> ResponseEntity.status(200).body(new ApiResponse(message));
        };

    }
}
