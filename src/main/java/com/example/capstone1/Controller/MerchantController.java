package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.MerchantService;
import com.example.capstone1.Service.MerchantStockService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;


    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("merchant added: "+merchant));
    }

    @GetMapping("/merchants")
    public ResponseEntity getMerchants(){
            return ResponseEntity.status(400).body(new ApiResponse("there are no merchants"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        Boolean isUpdated = merchantService.updateMerchant(id,merchant);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("merchant updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));

    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeMerchant(@PathVariable Integer id){
        Boolean isRemoved = merchantService.removeMerchant(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("merchant removed"));
        }else return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
    }


}
