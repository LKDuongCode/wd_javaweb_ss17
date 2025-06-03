package com.duong.ss17.service.hw03;

import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.repository.hw03.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getProductsPerPage(int pageNumber, int pageSize) {
        return productRepo.getProductsPerPage(pageNumber,pageSize);
    }

    @Override
    public int countProducts() {
        return productRepo.countProducts();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepo.findById(id);
    }
}
