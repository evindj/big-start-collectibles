package com.example.collectibles.controllers;

import com.example.collectibles.beans.Product;
import com.example.collectibles.dao.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.Executor;

@Controller
public class ProductController {

    private ProductRepository productRepository;
    // has to be the same name as the bean definition.
    private Executor asyncExecutor;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductRepository productRepository,
                             Executor asyncExecutor) {
        this.productRepository = productRepository;
        this.asyncExecutor = asyncExecutor;
    }
    @PostMapping("/search")
    public String search(@RequestParam("searchString") String keyword, Model model) {
        List<Product> products = productRepository.searchByName(keyword);
        model.addAttribute("products", products);
        model.addAttribute("searchedFor", keyword);
        return "search-results";
    }

    @GetMapping("/getAllProducts")
    public DeferredResult<String> getAllProducts(Model model) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        asyncExecutor.execute(()->{
            model.addAttribute("products", getProducts());
            deferredResult.setResult("product-list");
        });
        return deferredResult;
    }
    private Iterable<Product> getProducts() {
        logger.info("Getting all products we are on spring executor thread");
        try {
            Thread.sleep(6000);
        }
        catch (InterruptedException exception) {
            throw new RuntimeException();
        }
        return productRepository.findAll();
    }
}
