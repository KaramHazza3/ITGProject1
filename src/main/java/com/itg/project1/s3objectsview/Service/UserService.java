package com.itg.project1.s3objectsview.Service;

import com.itg.project1.s3objectsview.Config.CustomLoggerConfig;
import com.itg.project1.s3objectsview.Models.LoginData;
import com.itg.project1.s3objectsview.Models.User;
import com.itg.project1.s3objectsview.Models.UserData;
import com.itg.project1.s3objectsview.Repostiroies.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final CustomLoggerConfig customLoggerConfig;

    public UserService(UserRepository userRepository, CustomLoggerConfig customLoggerConfig) {
        this.userRepository = userRepository;
        this.customLoggerConfig = customLoggerConfig;
    }

    @Override
    public User register(UserData userData) {
        User newUser = new User(userData.getFullName(),userData.getEmail(),userData.getPassword());
        customLoggerConfig.logAction(newUser.getFullName() + " Has been created his account successfully");
        return userRepository.save(newUser);
    }

    @Override
    public boolean login(LoginData loginData) {
        User existedUser = userRepository.findByEmail(loginData.getEmail());
        if(existedUser == null) return false;
        if(existedUser.getPassword().equals(loginData.getPassword())) {
            customLoggerConfig.logAction(existedUser.getFullName() + " Has been logged successfully");
            return true;
        }
        customLoggerConfig.logAction(existedUser.getFullName() + " Has been written a wrong password");
        return false;
    }

}
