package com.example.talimet.user.service;

import com.example.talimet.common.enums.AccountStatus;
import com.example.talimet.user.dto.request.UserChangeStatusRequest;
import com.example.talimet.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> gettAllUsers();
    List<User> getAllInActiveUsers();
    User getMyInformation(UUID userId);
    User activateUser(UUID userId);
    User changeUserStatus(UserChangeStatusRequest dto);
    Long countOfUsers();
}
