package com.openclassrooms.nja.chatop.repository;

import com.openclassrooms.nja.chatop.entity.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The MessageRepository interface is a repository for managing MessageEntity objects.
 * It extends the JpaRepository interface, providing CRUD operations and additional querying functionality.
 */
@Repository// Marks this interface as a Repository component, indicating that it's used for encapsulating storage,
// retrieval, and search behavior which emulates a collection of objects.
public interface MessageRepository extends JpaRepository<MessagesEntity, Long> {
}