package com.openclassrooms.nja.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a DTO (Data Transfer Object) for rentals.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class RentalsDTO {
    private List<RentalConversionDTO> rentals; // List of rental RentalConversionDTO
}
