package dev.suraj.productservice.services;

import dev.suraj.productservice.dtos.FakeStoreProductDto;
import dev.suraj.productservice.dtos.GenericProductDto;
import dev.suraj.productservice.exceptions.NotFoundException;
import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    RestTemplateBuilder restTemplateBuilder;

    public static String fakeStoreProducturl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.
                getForEntity(fakeStoreProducturl+"/{id}", FakeStoreProductDto.class,id);

        HttpStatusCode responseCode = response.getStatusCode();
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: "+id+" doesn't exists.");
        }

        if(responseCode.equals(HttpStatus.OK)){
            return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        }

        return null;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(new Category(fakeStoreProductDto.getCategory()));
        return product;
    }

    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
                fakeStoreProducturl, product, GenericProductDto.class);

        return response.getBody();
    }

    @Override
    public Product deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.
                responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response =  restTemplate.execute(fakeStoreProducturl+"/{id}",
                HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return convertFakeStoreProductDtoToProduct(response.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(fakeStoreProducturl, FakeStoreProductDto[].class);

        List<Product> result = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: response.getBody()){
            result.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return result;
    }
}
