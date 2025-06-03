package com.duong.ss17.controller.hw06_hw07;

import com.duong.ss17.dto.hw06_hw07.CreateOrderDTO;
import com.duong.ss17.dto.hw06_hw07.OrderHistoryDTO;
import com.duong.ss17.entity.hw01_hw02.Customer;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.service.hw04_hw05.CartService;
import com.duong.ss17.service.hw06_hw07.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping("/checkout")
    public String showCheckoutForm(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) return "redirect:/auth/login";

        model.addAttribute("orderDTO", new CreateOrderDTO());
        return "hw03_order_form";
    }

    @PostMapping("/checkout")
    public String handleCheckout(@ModelAttribute("orderDTO") CreateOrderDTO dto,
                                 Model model,
                                 HttpSession session) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) return "redirect:/auth/login";

        List<Product> cartProducts = cartService.getProductsInCart(customer.getId());

        boolean success = orderService.insert(customer.getId(), cartProducts, dto);

        if (!success) {
            model.addAttribute("error", "Đặt hàng thất bại. Vui lòng thử lại!");
            return "hw03_order_form";
        }

        cartService.clearCart(customer.getId());
        model.addAttribute("success", "Đặt hàng thành công!");
        return "redirect:/products/1";
    }

    @GetMapping("/history")
    public String viewOrderHistory(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) return "redirect:/auth/login";

        List<OrderHistoryDTO> orders = orderService.getOrdersByCustomerId(customer.getId());
        model.addAttribute("orders", orders);
        return "hw03_order_history";
    }
}
