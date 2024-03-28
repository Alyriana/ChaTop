package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.RentalConversionDTO;
import com.openclassrooms.nja.chatop.dto.RentalDTO;
import com.openclassrooms.nja.chatop.dto.RentalsDTO;
import com.openclassrooms.nja.chatop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * Controller class for managing rental operations.
 */
@RestController // Marks this class as a REST controller, a special controller used in RESTful web services
@RequiredArgsConstructor
// Lombok's annotation that generates a constructor with 1 parameter for each field that requires special handling
@RequestMapping("/api/rentals") // Maps requests starting with "/api/rentals" to methods in this controller
public class RentalController {

    private final RentalService rentalService; // Injects the RentalService to use its functionalities

    // Annotated method to handle HTTP GET requests, fetching all rental records
    @Operation(summary = "Get rentals", description = "Return all rentals.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rentals found.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RentalsDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Invalid user. UNAUTHORIZED",
                            content = @Content)
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("") // Maps GET requests to this method
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(rentalService.getRentals()); // Returns the list of rentals with OK status
    }

    // Handles the creation of a new rental
    @Operation(summary = "Create rental", description = "Create a new rental.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rental created.",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "401", description = "Invalid user. UNAUTHORIZED",
                            content = @Content)
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("") // Maps POST requests to this method
    public ResponseEntity<?> createRental(@ModelAttribute RentalDTO rentalDTO) {
        rentalService.createRental(rentalDTO); // Calls the service to create a new rental
        return ResponseEntity.ok(Collections.singletonMap("message", "Rental created !")); // Returns a success message
    }

    // Fetches a specific rental by its ID
    @Operation(summary = "Get rental by id", description = "Return a rental by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rental found.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RentalConversionDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Invalid user. UNAUTHORIZED",
                            content = @Content)
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("{id}") // Maps GET requests with an ID to this method
    public ResponseEntity<?> getRentalById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rentalService.findByIdToDTO(id)); // Returns the found rental or an error if not found
    }

    // Updates an existing rental
    @Operation(summary = "Update rental", description = "Updating an existing rental.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rental updated.",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "401", description = "Invalid user. UNAUTHORIZED",
                            content = @Content)
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("{id}") // Maps PUT requests with an ID to this method
    public ResponseEntity<?> updateRental(@PathVariable("id") Long id, @ModelAttribute RentalDTO rentalDTO) {
        rentalService.updateRental(id, rentalDTO); // Calls the service to update the rental
        return ResponseEntity.ok(Collections.singletonMap("message", "Rental updated !")); // Returns a success message
    }
}
