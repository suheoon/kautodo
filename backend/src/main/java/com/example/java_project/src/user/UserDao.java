package com.example.java_project.src.user;

import com.example.java_project.src.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
    Boolean existsByUserId(String userId);
    UserEntity findByUserIdAndUserPassword(String userId, String userPassword);
}
