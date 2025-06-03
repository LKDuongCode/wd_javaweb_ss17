package com.duong.ss17.service.hw03;

import com.duong.ss17.entity.hw03.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProductsPerPage (int pageNumber, int pageSize);
    int countProducts ();
    Optional<Product> findById (int id);
}
