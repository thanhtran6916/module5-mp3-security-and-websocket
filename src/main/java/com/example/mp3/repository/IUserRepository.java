package com.example.mp3.repository;

import com.example.mp3.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
