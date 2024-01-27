package dev.suraj.productservice.controllers;

import dev.suraj.productservice.dtos.GenericProductDto;
import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);

    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") Long id){
        productService.deleteProductById(id);

    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
