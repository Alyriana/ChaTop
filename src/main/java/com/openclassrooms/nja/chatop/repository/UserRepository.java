package com.openclassrooms.nja.chatop.repository;

import com.openclassrooms.nja.chatop.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The UserRepository interface is used for encapsulating storage, retrieval, and search behavior
 * which emulates a collection of users in a database. It extends JpaRepository interface
 * and provides custom methods to find and check the existence of a user by their email.
 */
@Repository // Marks this interface as a Repository component, indicating that it's used for encapsulating storage,
// retrieval, and search behavior which emulates a collection of objects.
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    // Custom method to find a user by their email. It returns an Optional of UsersEntity,
    // which means it can handle the case where a user might not be found, avoiding null checks.
    Optional<UsersEntity> findByEmail(String email);

    // Custom method to check if a user exists by their email. It returns a boolean indicating
    // the presence or absence of a user with the given email in the database.
    boolean existsByEmail(String email);
}
