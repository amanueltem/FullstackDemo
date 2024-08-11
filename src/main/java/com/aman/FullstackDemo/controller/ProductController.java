package com.aman.FullstackDemo.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aman.FullstackDemo.model.Product;
import com.aman.FullstackDemo.model.ProductDto;
import com.aman.FullstackDemo.repository.ProductRepository;

import jakarta.validation.Valid;

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
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto,
    BindingResult result){
        System.out.println(productDto.getName());
        try{
        if(productDto.getImageFile()==null||productDto.getImageFile().isEmpty()){
            result.addError(new FieldError("prodocutDto","imageFile"
            ,"The image file is required."));
        }
        if(result.hasErrors()){
        return "products/CreateProduct";
        }

        //save image to the server
        MultipartFile image=productDto.getImageFile();
        Date createdAt=new Date();
        String storagefileName=createdAt.getTime()+"_"+image.getOriginalFilename();
        try{
            String uploadDir="public/images/";
            Path uploadPath=Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream=image.getInputStream()){
                Files.copy(inputStream,Paths.get(uploadDir+storagefileName)
                ,StandardCopyOption.REPLACE_EXISTING);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        catch(Exception ex){

        }
        Product product=new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storagefileName);
        repo.save(product);
        return "redirect:/products";
    }
    catch(Exception ex){
        return "products/CreateProduct";
    }
    }

    @GetMapping("/edit")
    public String showEditPage(
        Model model,
        @RequestParam int id){
            try{
                Product product=repo.findById(id).get();
                model.addAttribute("product",product);

                ProductDto productDto=new ProductDto();
                productDto.setName(product.getName());
                productDto.setBrand(product.getBrand());
                productDto.setCategory(product.getCategory());
                productDto.setPrice(product.getPrice());
                productDto.setDescription(product.getDescription());

                model.addAttribute("productDto",productDto);
            }
            catch(Exception ex){
                System.out.println("Exception: "+ex.getMessage());
                return "redirect:/products";
            }
            return "products/EditProduct";
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id){
        try{
            Product product=repo.findById(id).get();
            //delete product image
            Path imagePath=Paths.get("public/images/"+product.getImageFileName());
            try{
                Files.delete(imagePath);

            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }

            //delete product
            repo.delete(product);
        }
        catch(Exception ex){

        }
        return "redirect:/products";
    }
}
