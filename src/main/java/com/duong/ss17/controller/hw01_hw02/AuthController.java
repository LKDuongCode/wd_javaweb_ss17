package com.duong.ss17.controller.hw01_hw02;

import com.duong.ss17.dto.hw01_hw02.LoginUserDTO;
import com.duong.ss17.dto.hw01_hw02.RegisterCustomerDTO;
import com.duong.ss17.entity.hw01_hw02.Customer;
import com.duong.ss17.entity.hw01_hw02.Role;
import com.duong.ss17.service.hw01_hw02.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String showLoginForm (Model model){
        model.addAttribute("loginDTO", new LoginUserDTO());
        return "hw02_login";
    }

    @GetMapping("/register")
    public String showRegisterForm (Model model){
        model.addAttribute("registerDTO", new RegisterCustomerDTO());
        return "hw01_register";
    }

    @GetMapping("/user")
    public String goUserHome (){
        return "hw02_user_home";
    }

    @GetMapping("/admin")
    public String goDashboard (){
        return "hw02_dashboard";
    }


    @PostMapping("/register")
    public String handleRegister (@ModelAttribute("registerDTO") RegisterCustomerDTO dto){
        if(!authService.insert(dto)) return "hw01_register";

        return "redirect:/auth";
    }

    @PostMapping("/login")
    public String handleLogin (@ModelAttribute("loginDTO") LoginUserDTO dto){
        if(!authService.login(dto)) return "hw02_login";

        Optional<Customer> optionalCustomer = authService.findByEmail(dto.getEmail());
        if(optionalCustomer.isEmpty()) return "hw02_login";

        Customer c = optionalCustomer.get();
        if(c.getRole() == Role.ADMIN) return "redirect:/auth/admin";

        return "redirect:/auth/user";
    }

}
