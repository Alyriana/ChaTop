package com.openclassrooms.nja.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * A data transfer object (DTO) class representing a message conversion.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class MessageConversionDTO {

    private Integer id; // Unique identifier for the message

    @JsonProperty(value = "rental_id") // Specifies JSON property name for rentalId
    private Integer rentalId; // Identifier for the rental associated with the message

    @JsonProperty(value = "user_id") // Specifies JSON property name for userId
    private Integer userId; // Identifier for the user who created the message

    private String message; // The content of the message

    @JsonProperty(value = "created_at") // Specifies JSON property name for createdAt
    private LocalDateTime createdAt; // Timestamp when the message was created

    @JsonProperty(value = "updated_at") // Specifies JSON property name for updatedAt
    private LocalDateTime updatedAt; // Timestamp when the message was last updated
}
