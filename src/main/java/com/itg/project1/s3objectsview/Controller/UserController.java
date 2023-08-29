package com.itg.project1.s3objectsview.Controller;


import com.itg.project1.s3objectsview.Service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


}
