package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;
    public String addProduct(Product product){
        Boolean doesCategoryExist = categoryService.doesCategoryExist(product.getCategoryId());
        if(!doesCategoryExist){
            return "2";
        }
        productRepository.save(product);
        return "1";
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public String updateProduct(Integer id, Product product){
        boolean doesCategoryExist = categoryService.doesCategoryExist(product.getCategoryId());
        if(!doesCategoryExist){
            return "2";
        }
        Product p = productRepository.getById(id);
        if(p==null){
            return "3";
        }
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategoryId(product.getCategoryId());
        productRepository.save(p);
        return "1";
    }
    public Boolean deleteProduct(Integer id){
        Product product = productRepository.getById(id);
        if(product == null){
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
    public Double getProductPrice(Integer productId){
        return productRepository.getById(productId).getPrice();
    }

    public Product getProduct(Integer productId){
        return productRepository.getById(productId);
    }
    public boolean doesProductExists(Integer id){
        Product product = productRepository.getById(id);
        if(product == null){
            return false;
        }
        return true;
    }

    public Product getProductCopy(Integer productId) {
        Product product = productRepository.getById(productId);
        return new Product(product.getId(),product.getName(),product.getPrice(),product.getCategoryId());
    }
}
