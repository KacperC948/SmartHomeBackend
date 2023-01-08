package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByMac(String mac);
    User getUserById(int id);
    User getUserByUsername(String username);
}
