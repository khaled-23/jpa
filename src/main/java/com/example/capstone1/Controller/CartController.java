package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Cart;
import com.example.capstone1.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @PostMapping("/add/{userId}/{merchantStockId}")
    public ResponseEntity addToCart(@PathVariable Integer userId, @PathVariable Integer merchantStockId){
        String condition = cartService.addToCart(userId,merchantStockId);
        return switch (condition) {
            case "0" -> ResponseEntity.status(400).body("user doesn't exists");
            case "1" -> ResponseEntity.status(200).body("merchant stock doesn't exists");
            default -> ResponseEntity.status(200).body(new ApiResponse("added"));
        };
    }
    @GetMapping("/view/{userId}")
    public ResponseEntity viewCart(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(cartService.getCart(userId));
    }

    @DeleteMapping("/remove-item/{userId}/{merchantStockId}")
    public ResponseEntity removeFromCart(@PathVariable Integer userId, @PathVariable Integer merchantStockId){
       String condition = cartService.removeFromCart(userId,merchantStockId);
         return switch (condition) {
            case "0" -> ResponseEntity.status(400).body(new ApiResponse("not found"));
            case "1" -> ResponseEntity.status(200).body(new ApiResponse("item removed"));
            default -> throw new IllegalStateException("Unexpected value: " + condition);
        };

    }
}
