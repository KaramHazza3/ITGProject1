package com.itg.project1.s3objectsview.Repostiroies;

import com.itg.project1.s3objectsview.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String Email);
}

