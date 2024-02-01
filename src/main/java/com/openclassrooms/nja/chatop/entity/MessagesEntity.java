package com.openclassrooms.nja.chatop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "messages", schema = "chatop")
public class MessagesEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "rental_id", nullable = true)
    private Integer rentalId;

    @Basic
    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Basic
    @Column(name = "message", nullable = true, length = 2000)
    private String message;

    @Basic
    @CreatedDate
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    @Basic
    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
}
