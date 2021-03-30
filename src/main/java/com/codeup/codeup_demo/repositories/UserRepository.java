package com.codeup.codeup_demo.repositories;


import com.codeup.codeup_demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
