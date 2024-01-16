package com.openclassrooms.nja.chatop.repository;

import com.openclassrooms.nja.chatop.entity.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessagesEntity, Long> {
}