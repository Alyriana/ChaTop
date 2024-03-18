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
 * The MessagesEntity class represents a message entity in the database.
 * It is used to store information about a message sent.
 */
@AllArgsConstructor // Lombok's annotation to generate a constructor with all arguments
@NoArgsConstructor // Lombok's annotation to generate a no-argument constructor
@Builder // Lombok's annotation to enable the Builder pattern for this class
@Data // Lombok's annotation to generate getters, setters, equals, hashCode, and toString methods automatically
@Entity // Specifies that this class is an entity and is mapped to a database table
@Table(name = "messages", schema = "chatop") // Specifies the table and schema in the database that this entity maps to
public class MessagesEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the ID should be generated automatically
    @Id // Marks this field as the primary key of the entity
    @Column(name = "id", nullable = false) // Maps this field to the `id` column in the table
    private int id;

    @Basic // Indicates a basic attribute of the entity, this annotation is optional
    @Column(name = "rental_id", nullable = true) // Maps this field to the `rental_id` column, which is nullable
    private Integer rentalId;

    @Basic
    @Column(name = "user_id", nullable = true) // Maps this field to the `user_id` column
    private Integer userId;

    @Basic
    @Column(name = "message", nullable = true, length = 2000)
    // Maps this field to the `message` column with a specified length
    private String message;

    @Basic
    @CreatedDate // Indicates that this field is a timestamp for when the entity was created
    @Column(name = "created_at", nullable = true) // Maps this field to the `created_at` column
    private Timestamp createdAt;

    @Basic
    @LastModifiedDate // Indicates that this field is a timestamp for when the entity was last updated
    @Column(name = "updated_at", nullable = true) // Maps this field to the `updated_at` column
    private Timestamp updatedAt;
}
