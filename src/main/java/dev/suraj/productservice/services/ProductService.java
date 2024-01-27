package dev.suraj.productservice.services;

import dev.suraj.productservice.dtos.GenericProductDto;
import dev.suraj.productservice.exceptions.NotFoundException;
import dev.suraj.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto product);
    public Product deleteProductById(Long id);

    public List<Product> getAllProducts();
}
