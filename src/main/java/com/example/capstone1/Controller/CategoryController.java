package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("category added"));
    }

    @GetMapping("/categories")
    public ResponseEntity getCategories(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = categoryService.updateCategory(id, category);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("category updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("category not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        Boolean isRemoved = categoryService.removeCategory(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("category removed"));
        }else return ResponseEntity.status(200).body(new ApiResponse("category not found"));
    }
}
