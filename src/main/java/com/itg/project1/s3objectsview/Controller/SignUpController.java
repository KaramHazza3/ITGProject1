package com.itg.project1.s3objectsview.Controller;

import com.itg.project1.s3objectsview.Models.UserData;
import com.itg.project1.s3objectsview.Service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {
    private final IUserService userService;

    public SignUpController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignUpPage(Model model){
        model.addAttribute("userData", new UserData());
        return "SignUp";
    }

    @PostMapping("/signup")
    public String processSignUpForm(@ModelAttribute UserData userData, Model model) {
        userService.register(userData);
        model.addAttribute("loginError",false);
        return "redirect:/login";

    }
}
