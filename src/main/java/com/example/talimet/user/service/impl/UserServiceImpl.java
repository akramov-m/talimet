package com.example.talimet.user.service.impl;

import com.example.talimet.common.enums.AccountStatus;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.user.dto.request.UserChangeStatusRequest;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import com.example.talimet.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> gettAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<User> getAllInActiveUsers() {
        List<User> users = userRepository.findAllByStatus(AccountStatus.INACTIVE);
        return users;
    }

    @Override
    public User getMyInformation(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found!"));
        return user;
    }

    @Override
    @Transactional
    public User activateUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found!"));
        user.setStatus(AccountStatus.ACTIVE);
        return user;
    }

    @Override
    @Transactional
    public User changeUserStatus(UserChangeStatusRequest dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(()->new NotFoundException("User not found!"));
        user.setStatus(dto.status());
        return user;
    }

    @Override
    public Long countOfUsers() {
        Long counts = userRepository.countOfUsers();
        return counts;
    }
}
