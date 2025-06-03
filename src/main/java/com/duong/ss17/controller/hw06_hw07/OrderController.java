package com.duong.ss17.controller.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.entity.hw04_hw05.Cart;
import com.duong.ss17.entity.hw06_hw07.Order;
import com.duong.ss17.service.hw04_hw05.CartService;
import com.duong.ss17.service.hw06_hw07.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;

    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String goCheckout(@RequestParam("customerId") int customerId, Model model) {
        List<Cart> carts = cartService.getCartByCustomer(customerId);
        double total = cartService.getCartTotal(customerId);

        CreateOrderDTO orderDTO = new CreateOrderDTO(customerId, total);

        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("carts", carts);
        return "hw06_checkout";
    }


    @PostMapping("/confirm")
    public String confirmOrder(@ModelAttribute("orderDTO") CreateOrderDTO dto,
                               RedirectAttributes redirectAttributes) {
        boolean success = orderService.createOrder(dto);

        if (success) {
            List<Cart> carts = cartService.getCartByCustomer(dto.getCustomerId());
            for (Cart c : carts) {
                cartService.removeCart(c.getId());
            }

            redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Đặt hàng thất bại!");
        }

        return "redirect:/orders/history/" + dto.getCustomerId();
    }

    @GetMapping("/history/{customerId}")
    public String viewHistory(@PathVariable("customerId") int customerId, Model model) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        model.addAttribute("orders", orders);
        return "hw07_profile";
    }
}
