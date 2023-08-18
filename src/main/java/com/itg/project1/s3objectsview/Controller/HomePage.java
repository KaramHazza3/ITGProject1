package com.itg.project1.s3objectsview.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {
    @GetMapping("/homepage")
    public String showHomePage(){
        return "HomePage.html";
    }
}
