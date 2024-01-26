package dev.suraj.productservice.controllers;

import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(){
        return "Hello World";
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public void createProduct(){

    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
