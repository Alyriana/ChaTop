package com.openclassrooms.nja.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The MessageDTO class represents a data transfer object for a message.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class MessageDTO {

    @NotNull(message = "Message cannot be empty") // Ensures message content is not null
    @Size(max = 2000, message = "Message length must be less than 2000 characters.") // Limits message length
    private String message;

    @JsonProperty(value = "user_id") // Specifies JSON property name for userId
    @NotNull // Ensures userId is not null
    private Integer userId; // User ID of the message sender

    @JsonProperty(value = "rental_id") // Specifies JSON property name for rentalId
    @NotNull // Ensures rentalId is not null
    private Integer rentalId; // Rental ID associated with the message
}
