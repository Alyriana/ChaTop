package com.openclassrooms.nja.chatop.repository;

import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The RentalRepository interface is responsible for managing rental entities in the database.
 * It extends the JpaRepository interface, providing CRUD operations and additional methods.
 */
@Repository// Marks this interface as a Repository component, indicating that it's used for encapsulating storage,
// retrieval, and search behavior which emulates a collection of objects.
public interface RentalRepository extends JpaRepository<RentalsEntity, Long> {
}
