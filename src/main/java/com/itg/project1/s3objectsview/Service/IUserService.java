package com.itg.project1.s3objectsview.Service;

import com.itg.project1.s3objectsview.Models.LoginData;
import com.itg.project1.s3objectsview.Models.User;
import com.itg.project1.s3objectsview.Models.UserData;

public interface IUserService {
    User register(UserData userData);
    boolean login(LoginData loginData);
}
