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
@Table(name = "rentals", schema = "chatop")
public class RentalsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "surface", nullable = true, precision = 0)
    private Integer surface;

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Integer price;

    @Basic
    @Column(name = "picture", nullable = true, length = 255)
    private String picture;

    @Basic
    @Column(name = "description", nullable = true, length = 2000)
    private String description;

    @Basic
    @Column(name = "owner_id", nullable = false)
    private int ownerId;

    @Basic
    @CreatedDate
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    @Basic
    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
}
