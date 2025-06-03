package com.duong.ss17.controller.hw04_hw05;

import com.duong.ss17.dto.hw04_hw05.AddCartDTO;
import com.duong.ss17.entity.hw04_hw05.Cart;
import com.duong.ss17.service.hw04_hw05.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("customerId") int customerId,
                            @RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            RedirectAttributes redirectAttributes) {
        AddCartDTO dto = new AddCartDTO(customerId, productId, quantity);
        boolean success = cartService.addToCart(dto);

        if (success) {
            redirectAttributes.addFlashAttribute("message", "Add to cart success!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to add to cart.");
        }

        return "redirect:/products/1";
    }

    @GetMapping("/view/{customerId}")
    public String viewCart(@PathVariable("customerId") int customerId, Model model) {
        List<Cart> carts = cartService.getCartByCustomer(customerId);
        double total = cartService.getCartTotal(customerId);

        model.addAttribute("carts", carts);
        model.addAttribute("total", total);
        return "hw04_cart_list";
    }
}
