package com.openclassrooms.nja.chatop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

/**
 * Represents a rental entity.
 */
@AllArgsConstructor // Lombok's annotation to generate a constructor with all arguments
@NoArgsConstructor // Lombok's annotation to generate a no-argument constructor
@Builder // Lombok's annotation to enable the Builder pattern for this class
@Data // Lombok's annotation to generate getters, setters, equals, hashCode, and toString methods automatically
@Entity // Specifies that this class is an entity and is mapped to a database table
@Table(name = "rentals", schema = "chatop") // Specifies the name of the table and schema this entity is mapped to
public class RentalsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicates that the ID should be generated automatically
    @Id // Marks this field as the primary key of the entity
    @Column(name = "id", nullable = false) // Maps this field to the `id` column in the table
    private int id;

    @Basic // Indicates a simple attribute of the entity
    @Column(name = "name", nullable = true, length = 255) // Maps this field to the `name` column with a maximum length
    private String name;

    @Basic
    @Column(name = "surface", nullable = true, precision = 0)
    // Maps this field to the `surface` column, precision is not specifically required here
    private Integer surface;

    @Basic
    @Column(name = "price", nullable = true, precision = 0) // Maps this field to the `price` column
    private Integer price;

    @Basic
    @Column(name = "picture", nullable = true, length = 255)
    // Maps this field to the `picture` column with a maximum length
    private String picture;

    @Basic
    @Column(name = "description", nullable = true, length = 2000)
    // Maps this field to the `description` column with a specified length limit
    private String description;

    @Basic
    @Column(name = "owner_id", nullable = false) // Maps this field to the `owner_id` column, cannot be null
    private int ownerId;

    @Basic
    @CreatedDate // Indicates that this field is automatically populated with the creation date
    @Column(name = "created_at", nullable = true) // Maps this field to the `created_at` column
    private Timestamp createdAt;

    @Basic
    @LastModifiedDate // Indicates that this field is automatically updated with the last modification date
    @Column(name = "updated_at", nullable = true) // Maps this field to the `updated_at` column
    private Timestamp updatedAt;
}
