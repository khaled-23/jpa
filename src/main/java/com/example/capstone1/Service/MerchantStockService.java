package com.example.capstone1.Service;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockRepository merchantStockRepository;

    public String addMerchantStock(MerchantStock merchantStock){
        boolean doesMerchantExists = merchantService.doesMerchantExists(merchantStock.getMerchantId());
        if(!doesMerchantExists){
            return "0";
        }
        boolean doesProductExists = productService.doesProductExists(merchantStock.getProductId());
        if(!doesProductExists){
            return "1";
        }
        merchantStockRepository.save(merchantStock);
        return "2";
    }

    public List<MerchantStock> getMerchantStocks(){
        return merchantStockRepository.findAll();
    }

    public Boolean updateMerchantStock(Integer id, MerchantStock merchantStock){
        MerchantStock m = merchantStockRepository.getById(id);
        if(m==null){
            return false;
        }
        m.setStock(merchantStock.getStock());
        m.setProductId(merchantStock.getProductId());
        m.setMerchantId(merchantStock.getMerchantId());
        merchantStockRepository.save(m);
        return true;
    }

    public Boolean removeMerchantStock(Integer id){
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if(merchantStock == null){
            return false;
        }
        merchantStockRepository.deleteById(id);
        return true;
    }

    public boolean doesMerchantAndProductExists(Integer merchantStockId){
        MerchantStock merchantStock = merchantStockRepository.getById(merchantStockId);
        if(merchantStock == null){
            return false;
        }
        return true;
    }

    public String reStock(Integer id, Integer merchantId, Integer productId, int stock){
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if(merchantStock == null){
            return "0";
        }
        boolean isPositive = 0<stock;
        if(!isPositive){
            return "1";
        }
        merchantStock.setStock(merchantStock.getStock()+stock);
        return "2";
    }
    public boolean hasStock(Integer merchantStockId){
        MerchantStock merchantStock = merchantStockRepository.getById(merchantStockId);
        if(merchantStock.getStock()>0){
            return true;
        }
        return false;
    }

    public void userOrdered(Integer merchantStockId) {
        MerchantStock merchantStock = merchantStockRepository.getById(merchantStockId);
        merchantStock.setStock(merchantStock.getStock()-1);
        merchantStockRepository.save(merchantStock);
    }

}