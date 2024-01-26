package dev.suraj.productservice.services;

import dev.suraj.productservice.models.Product;

public interface ProductService {
    Product getProductById(Long id);
}
