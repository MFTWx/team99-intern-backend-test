package com.exercise.listingservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exercise.listingservice.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    
}