package com.openclassrooms.nja.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class UserConversionDTO {
    private Integer id; // Unique identifier for the user

    private String email; // Email of the user

    private String name; // Name of the user

    @JsonProperty(value = "created_at") // Specifies JSON property name for createdAt
    private LocalDateTime createdAt; // Timestamp when the user account was created

    @JsonProperty(value = "updated_at") // Specifies JSON property name for updatedAt
    private LocalDateTime updatedAt; // Timestamp when the user account was last updated
}
