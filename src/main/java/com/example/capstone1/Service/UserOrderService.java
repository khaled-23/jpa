package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.UserOrder;
import com.example.capstone1.Repository.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;
    public void addOrder(UserOrder userOrder){
        userOrderRepository.save(userOrder);
    }

    public List<UserOrder> getUserOrders(Integer userId) {
        List<UserOrder> userOrders = userOrderRepository.findAll();
        ArrayList<UserOrder> userOrdersById = new ArrayList<>();
        for(UserOrder userOrder:userOrders){
            if(userOrder.getUserId().equals(userId)){
                userOrdersById.add(userOrder);
            }
        }
        return userOrdersById;
    }

}
