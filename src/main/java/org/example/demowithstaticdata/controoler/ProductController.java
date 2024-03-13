package org.example.demowithstaticdata.controoler;

import org.example.demowithstaticdata.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {
    private final List<Product> productList = new ArrayList<>();
    @GetMapping
    public String viewProducts(Model model) {
        model.addAttribute("products", productList);
        return "products/index";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product("", 0.0));
        return "products/add";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productList.add(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = findProductById(id);
        model.addAttribute("product", product);
        deleteProduct(id);
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@ModelAttribute Product product) {
        productList.add(product);
        
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productList.removeIf(product -> product.getId() == id);
        return "redirect:/products";
    }
    private Product findProductById(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

}
