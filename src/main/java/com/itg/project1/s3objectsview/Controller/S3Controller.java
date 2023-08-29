package com.itg.project1.s3objectsview.Controller;

import com.itg.project1.s3objectsview.Service.IS3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class S3Controller {

    private IS3Services s3services;
    @Autowired
    public S3Controller(IS3Services s3services){
        this.s3services = s3services;
    }

    @GetMapping("/gallery")
    public String showGallery(Model model){
        List<String> images = s3services.getAllImagesFromS3Bucket();
        System.out.println(images);
        model.addAttribute("images",images);
        return "Gallery.html";
    }
}
