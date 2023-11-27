package com.example.collectibles.restcontrollers;

import com.example.collectibles.beans.Product;
import com.example.collectibles.dao.ProductRepository;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductRestController {
    private ProductRepository productRepository;

    ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    /*
    @GetMapping("/bigstar/api/products")

    public List<Product> allProducts() {
        List<Product> products = new ArrayList<>();
        return (List<Product>) productRepository.findAll();
    }
    */

    @GetMapping("/bigstar/api/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(Streamable.of(productRepository.findAll()).stream().toList(), HttpStatus.OK);
    }

    @GetMapping("/bigstar/api/products/{id}")

    public Product getProductById(@PathVariable("id") String id) {
        return productRepository.findById(Integer.valueOf(id)).orElseThrow(()-> new ProductNotFoundException(id));
    }

    @PostMapping("/bigstar/api/products")
    public Product saveProduct(@RequestBody Product newProduct) {
        return productRepository.findById(newProduct.getId()).map(product -> {
            product.setName(newProduct.getName());
            product.setColor(newProduct.getColor());
            product.setImagePath(newProduct.getImagePath());
            product.setRobotId(newProduct.getRobotId());
            product.setRating(newProduct.getRating());
            product.setNoOfReviews(newProduct.getNoOfReviews());
            product.setDescription(newProduct.getDescription());
            product.setCategoryId(newProduct.getCategoryId());
            return productRepository.save(product);
        }).orElseGet(()->productRepository.save(newProduct));
    }
}
