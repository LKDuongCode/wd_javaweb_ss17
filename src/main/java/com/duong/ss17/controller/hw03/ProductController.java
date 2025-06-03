package com.duong.ss17.controller.hw03;

import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.service.hw03.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{page}")
    public String showProductList (@PathVariable("page") int page, Model model){
        int pageSize = 5;
        List<Product> products = productService.getProductsPerPage(page,pageSize);
        int totalProducts = productService.countProducts();
        int totalPages = (int) Math.ceil((double) totalProducts/pageSize);

        model.addAttribute("products",products);
        model.addAttribute("curPage",page);
        model.addAttribute("totalPage", totalPages);

        return "hw03_product_list";
    }

    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable("id") int id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isEmpty()) {
            return "redirect:/products/1";
        }

        model.addAttribute("product", optionalProduct.get());
        return "hw03_product_detail";
    }
}
