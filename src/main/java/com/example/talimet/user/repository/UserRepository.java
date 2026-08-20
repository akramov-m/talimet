package com.example.talimet.user.repository;

import com.example.talimet.common.enums.AccountStatus;
import com.example.talimet.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findById(UUID uuid);
    List<User> findAllByStatus(AccountStatus status);

    @Query("""
    SELECT COUNT(DISTINCT u.id)
    FROM User u
    """)
    Long countOfUsers();
}
