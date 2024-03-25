package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantStockService merchantStockService;
    private final MerchantRepository merchantRepository;



    public void addMerchant(Merchant merchant){
        merchantRepository.save(merchant);
    }
    public List<Merchant> getMerchants(){
        return merchantRepository.findAll();
    }

    public Boolean updateMerchant(Integer id, Merchant merchant){
        Merchant m = merchantRepository.getById(id);
        if(m == null){
            return false;
        }
        m.setName(m.getName());
        return true;
    }
    public Boolean removeMerchant(Integer id){
        Merchant merchant = merchantRepository.getById(id);
        if(merchant == null){
            return false;
        }
        merchantRepository.deleteById(id);
        return true;
    }




    public boolean doesMerchantExists(Integer id) {
        Merchant merchant = merchantRepository.getById(id);
        if(merchant == null){
            return false;
        }
        return true;
    }
}
