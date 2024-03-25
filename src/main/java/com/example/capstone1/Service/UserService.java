package com.example.capstone1.Service;


import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Model.UserOrder;
import com.example.capstone1.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final UserOrderService userOrderService;
    private final CouponService couponService;
    private final UserRepository userRepository;


    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Boolean updateUser(Integer id, User user){
        User u = userRepository.getById(id);
        if(u == null){
            return false;
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setBalance(user.getBalance());
        userRepository.save(u);
        return true;
    }
    public Boolean removeUser(Integer id){
        User user = userRepository.getById(id);
        if(user == null){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    public String buyProduct(Integer userId, Integer merchantStockId, Integer merchantId, Integer productId){
        User user = userRepository.getById(userId);
        if(user == null){
            return "user does not exists";
        }

        boolean doesMerchantAndProductExists = merchantStockService.doesMerchantAndProductExists(merchantStockId);
        if(!doesMerchantAndProductExists){
            return "merchantStock does not exists";
        }
        boolean hasStock = merchantStockService.hasStock(merchantStockId);
        if(!hasStock){
            return "merchant does not have stock";
        }
        Double productPrice = productService.getProductPrice(productId);
        boolean userHasEnoughBalance = productPrice <= getUserBalance(userId);
        if(!userHasEnoughBalance){
            return "user does not have enough credit";}
        Product product = productService.getProductCopy(productId);
        order(userId,productPrice);
        userOrderService.addOrder(new UserOrder(0, userId, LocalDateTime.now(),productId)); //order history
        merchantStockService.userOrdered(merchantStockId);//reduce stock by 1
        return "product bought";
    }
    public String buyWithCoupon(Integer userId, Integer merchantStockId,Integer merchantId,Integer productId,Integer couponId){
        boolean isExists = isExists(userId);
        if(!isExists){
            return "user does not exists";
        }
        boolean doesMerchantAndProductExists = merchantStockService.doesMerchantAndProductExists(merchantStockId);
        if(!doesMerchantAndProductExists){
            return "merchantStock does not exists";
        }
        boolean hasStock = merchantStockService.hasStock(merchantStockId);
        if(!hasStock){
            return "merchant does not have stock";
        }
        Double productPrice = productService.getProductPrice(productId);
        double couponPercent = couponService.getCouponPercent(couponId);
        double productPriceWithDiscount = couponService.calculatePrice(productPrice, couponPercent);
        boolean userHasEnoughBalance = productPriceWithDiscount <= getUserBalance(userId);
        if(!userHasEnoughBalance){
            return "user does not have enough credit";}
        boolean isCouponValid = couponService.isCouponValid(couponId, merchantStockId);
        if(!isCouponValid){return "coupon is invalid";}
        Product product = productService.getProductCopy(productId);
        product.setPrice(productPriceWithDiscount);//to save product in order history with price after discount
        order(userId,productPriceWithDiscount);
        userOrderService.addOrder(new UserOrder(0,userId, LocalDateTime.now(),productId)); //order history
        merchantStockService.userOrdered(merchantStockId);//reduce stock by 1
        couponService.reduceUses(couponId);
        return "product bought";

    }
    public boolean isExists(Integer userId){
        User user = userRepository.getById(userId);
        if(user == null){
            return false;
        }
        return true;
    }
    public double getUserBalance(Integer userId){
        User user = userRepository.getById(userId);
        if(user==null){
            return 0;
        }
        return user.getBalance();
    }

    public void order(Integer userId,double price){
        User user = userRepository.getById(userId);
        user.setBalance(user.getBalance()-price);
        userRepository.save(user);
    }
    public List<UserOrder> getOrderHistory(Integer userId) {
        return userOrderService.getUserOrders(userId);
    }
}
