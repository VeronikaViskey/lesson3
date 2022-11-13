package com.geekbrains.lesson3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/seeAllProductsPage/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable int id) {
        Product product = productRepository.getProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        return product;
    }


    @GetMapping("/seeAllProductsPage")
    public String showAllProducts(Model model){
        model.addAttribute("productsList",productRepository.getProducts());
        return "seeAllProductsPage";
    }

    @GetMapping("/addProductPage")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "addProductPage";
    }

    @PostMapping("/addProductPage")
    @ResponseBody
    public String submitForm(@ModelAttribute("product") Product product) {
        if (product.getId() == (int)product.getId()
                || productRepository.getProducts().stream().filter(p -> p.getId() == product.getId()).findFirst().orElse(null) != null
                || ! (product.getTitle() instanceof String
                || !(product.getCost() instanceof Double))){
            return "Failure";
        }
        productRepository.getProducts().add(product);
        return product.toString() + " added sucessfully";
    }

}
