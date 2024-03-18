package com.openclassrooms.nja.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a Rental Conversion Data Transfer Object (DTO).
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class RentalConversionDTO {
    private Integer id; // Unique identifier for the rental

    private String name; // Name of the rental property

    private Integer surface; // Surface area of the rental property

    private Integer price; // Rental price

    private String picture; // URL or reference to the rental picture

    private String description; // Description of the rental property

    @JsonProperty(value = "owner_id") // Specifies JSON property name for ownerId
    private Integer ownerId; // ID of the owner of the rental property

    @JsonProperty(value = "created_at") // Specifies JSON property name for createdAt
    private LocalDateTime createdAt; // Timestamp when the rental was created

    @JsonProperty(value = "updated_at") // Specifies JSON property name for updatedAt
    private LocalDateTime updatedAt; // Timestamp when the rental was last updated
}
