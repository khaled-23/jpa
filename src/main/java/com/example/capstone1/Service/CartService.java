package com.example.capstone1.Service;

import com.example.capstone1.Model.Cart;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Repository.CartRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MerchantStockService merchantStockService;
    private final UserService userService;

    public String addToCart(Integer userId, Integer merchantStockId) {
        boolean doesUserExists = userService.isExists(userId);
        if(!doesUserExists){
            return "0";
        }
        boolean doesStockExists = merchantStockService.doesMerchantAndProductExists(merchantStockId);
        if(!doesStockExists){
            return "1";
        }

        cartRepository.save((new Cart(userId,merchantStockId)));
        return "default";
    }

    public List<Cart> getCart(Integer userId) {
        List<Cart> c = cartRepository.findAll();
        List<Cart> userCart = new ArrayList<>();
        for(Cart cart:c){
            if(cart.getUserId().equals(userId)){
                userCart.add(cart);
            }
        }
        return userCart;
    }

    public String removeFromCart(Integer userId, Integer merchantStockId) {
        List<Cart> carts = cartRepository.findAll();
        for(int i=0; i<carts.size();i++){
            if(carts.get(i).getUserId().equals(userId)
            && carts.get(i).getMerchantStockId().equals(merchantStockId)){
            cartRepository.delete(carts.remove(i));
            return "1";}
        }
        return "0";
    }
}
