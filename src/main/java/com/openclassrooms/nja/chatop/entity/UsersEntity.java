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
 * Represents a user entity in the database.
 */
@AllArgsConstructor // Lombok's annotation to generate a constructor with all arguments
@NoArgsConstructor // Lombok's annotation to generate a no-argument constructor
@Builder // Lombok's annotation to enable the Builder pattern for this class
@Data // Lombok's annotation to generate getters, setters, equals, hashCode, and toString methods automatically
@Entity // Specifies that this class is an entity and is mapped to a database table
@Table(name = "users", schema = "chatop") // Specifies the table and schema this entity maps to
public class UsersEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the primary key is auto-generated
    @Id // Marks this field as the primary key
    @Column(name = "id", nullable = false) // Maps this field to the `id` column in the table
    private int id;

    @Basic // Marks this as a basic attribute of the entity
    @Column(name = "email", nullable = true, length = 255) // Maps this field to the `email` column
    private String email;

    @Basic
    @Column(name = "name", nullable = true, length = 255) // Maps this field to the `name` column
    private String name;

    @Basic
    @Column(name = "password", nullable = true, length = 255) // Maps this field to the `password` column
    private String password;

    @Basic
    @CreatedDate // Indicates that this field stores the date this record was created
    @Column(name = "created_at", nullable = true) // Maps this field to the `created_at` column
    private Timestamp createdAt;

    @Basic
    @LastModifiedDate // Indicates that this field stores the date this record was last updated
    @Column(name = "updated_at", nullable = true) // Maps this field to the `updated_at` column
    private Timestamp updatedAt;
}
