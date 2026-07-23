package com.example.talimet.user.service.impl;

import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import com.example.talimet.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> gettAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
