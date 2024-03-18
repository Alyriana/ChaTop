package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Represents a Rental DTO (Data Transfer Object) used for creating and updating rental records.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class RentalDTO {
    @NotNull(message = "Name cannot be empty") // Ensures name content is not null
    private String name; // Name of the rental

    @NotNull(message = "Surface cannot be empty") // Ensures surface content is not null
    private Integer surface; // Surface area of the rental

    @NotNull(message = "Price cannot be empty") // Ensures price content is not null
    private Integer price; // Price of the rental

    private MultipartFile picture; // Picture file of the rental, optional for the request

    @NotNull(message = "Description cannot be empty") // Ensures description content is not null
    @Size(max = 2000, message = "Description length must be less than 2000 characters.") // Limits description length
    private String description; // Description of the rental, with a maximum length
}
