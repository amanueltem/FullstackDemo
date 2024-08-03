package com.aman.FullstackDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aman.FullstackDemo.model.Product;
import com.aman.FullstackDemo.model.ProductDto;
import com.aman.FullstackDemo.repository.ProductRepository;

@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository repo;
    @GetMapping({"","/"})
    public String showProductList(Model model){
        List<Product> products=repo.findAll();
        model.addAttribute("products",products);
        return "products/index";
    }
    @GetMapping("/create")
    public String showCreatePage(Model model){
        ProductDto productDto=new ProductDto();
        model.addAttribute("productDto",productDto);
        return "products/CreateProduct";
    }
}