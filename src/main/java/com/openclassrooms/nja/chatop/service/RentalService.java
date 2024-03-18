package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.RentalConversionDTO;
import com.openclassrooms.nja.chatop.dto.RentalDTO;
import com.openclassrooms.nja.chatop.dto.RentalsDTO;
import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import com.openclassrooms.nja.chatop.exception.NotFoundException;
import com.openclassrooms.nja.chatop.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The RentalService class provides methods for managing rental records.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class RentalService {
    // Dependencies injected by Spring's DI container.
    private final RentalRepository rentalRepository;
    private final UserService userService;
    private final StorageService storageService;
    private final ConversionService conversionService;

    // Retrieves all rental records from the database, converts them to DTOs, and returns them.
    public RentalsDTO getRentals() {
        List<RentalsEntity> entities = rentalRepository.findAll(); // Fetches all rental entities.
        // Converts each entity to a DTO using the ConversionService.
        List<RentalConversionDTO> rentals = entities.stream()
                .map(conversionService::rentalToDTO)
                .collect(Collectors.toList());
        return new RentalsDTO(rentals); // Wraps the list of DTOs in a RentalsDTO object.
    }

    // Finds a single rental by its ID and converts it to a DTO.
    public RentalConversionDTO findByIdToDTO(Long id) {
        return conversionService.rentalToDTO(findById(id)); // Converts the found rental entity to a DTO.
    }

    // Finds a single rental entity by its ID or throws a NotFoundException if not found.
    public RentalsEntity findById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rental not found with id: " + id));
    }

    // Creates a new rental record in the database based on the provided RentalDTO.
    @Transactional
    public void createRental(RentalDTO rentalDTO) {
        // Builds a RentalsEntity from the DTO and additional info, then saves it.
        rentalRepository.save(RentalsEntity.builder()
                .name(rentalDTO.getName())
                .surface(rentalDTO.getSurface())
                .price(rentalDTO.getPrice())
                .description(rentalDTO.getDescription())
                // Finds the current user's ID to set as the owner ID.
                .ownerId(userService.findByEmail(userService.getCurrentAuthenticatedUserName()).getId())
                // Uploads the picture and sets the URL/path.
                .picture(storageService.uploadPicture(rentalDTO.getPicture()))
                .createdAt(Timestamp.from(Instant.now())) // Sets the creation timestamp.
                .build());
    }

    // Updates an existing rental record with new information from the provided RentalDTO.
    @Transactional
    public void updateRental(Long id, RentalDTO rentalDTO) {
        RentalsEntity rentalToUpdate = findById(id); // Fetches the existing rental.
        // Updates the entity with new values from the DTO.
        rentalToUpdate.setName(rentalDTO.getName());
        rentalToUpdate.setSurface(rentalDTO.getSurface());
        rentalToUpdate.setPrice(rentalDTO.getPrice());
        rentalToUpdate.setDescription(rentalDTO.getDescription());
        rentalToUpdate.setUpdatedAt(Timestamp.from(Instant.now())); // Updates the modification timestamp.
        rentalRepository.save(rentalToUpdate); // Saves the updated entity.
    }
}
