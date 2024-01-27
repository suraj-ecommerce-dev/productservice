package dev.suraj.productservice.services;

import dev.suraj.productservice.dtos.GenericProductDto;
import dev.suraj.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    public GenericProductDto createProduct(GenericProductDto product);
    public void deleteProductById(Long id);

    public List<Product> getAllProducts();
}
