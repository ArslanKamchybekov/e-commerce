package com.example.e_commerce;

import com.example.e_commerce.models.Product;
import com.example.e_commerce.models.ProductDto;
import com.example.e_commerce.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService service;

    @GetMapping
    private String getProducts(Model model){
        model.addAttribute("products", service.getProducts());
        return "products/index";
    }

    @GetMapping("/create")
    private String createProduct(Model model){
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    private String addProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result){
        if(result.hasErrors()) return "products/CreateProduct";

        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(new Date());
        product.setImageFileName("Product image"); //fix the image issue

        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    private String editProduct(Model model, @RequestParam int id){
        try{
            Product product = service.get(id);
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            model.addAttribute("productDto", productDto);
        }catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    @PostMapping("/edit")
    private String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDto productDto, BindingResult result){
        Product product = service.get(id);
        model.addAttribute("product", product);
        if(result.hasErrors()) return "products/EditProduct";

        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageFileName("Product image"); //fix the image issue

        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete")
    private String removeProduct(@RequestParam int id){
        try{
            service.delete(id);
        }catch (Exception exception){
            System.out.println("Exception: " + exception.getMessage());
        }
        return "redirect:/products";
    }
}
