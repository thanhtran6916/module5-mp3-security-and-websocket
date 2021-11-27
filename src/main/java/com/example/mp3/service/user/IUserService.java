package com.example.mp3.service.user;

import com.example.mp3.model.User;
import com.example.mp3.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String name);
}
