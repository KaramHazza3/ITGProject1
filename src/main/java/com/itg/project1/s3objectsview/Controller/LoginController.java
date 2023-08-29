package com.itg.project1.s3objectsview.Controller;

import com.itg.project1.s3objectsview.Models.LoginData;
import com.itg.project1.s3objectsview.Service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final IUserService userService;

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLoginPage(Model model){
        model.addAttribute("loginData",new LoginData());
        model.addAttribute("loginError", false);
        return "Login";
    }
    @PostMapping
    public String processLoginForm(LoginData loginData, Model model){
        boolean existed = userService.login(loginData);
        if(existed) {
            return "HomePage";
        }
        model.addAttribute("loginError",true);
        return "Login";
    }
}
