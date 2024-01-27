package dev.suraj.productservice.controllers;

import dev.suraj.productservice.dtos.GenericProductDto;
import dev.suraj.productservice.exceptions.NotFoundException;
import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id){
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                productService.deleteProductById(id),
                HttpStatus.OK
        );
        return responseEntity;
    }

}
