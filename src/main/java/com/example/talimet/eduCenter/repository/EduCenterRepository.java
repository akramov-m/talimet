package com.example.talimet.eduCenter.repository;

import com.example.talimet.eduCenter.entity.EduCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EduCenterRepository extends JpaRepository<EduCenter, UUID> {
    Optional<EduCenter> findById(UUID uuid);
}
