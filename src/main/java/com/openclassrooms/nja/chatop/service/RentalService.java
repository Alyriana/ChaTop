package com.openclassrooms.nja.chatop.service;

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

@RequiredArgsConstructor
@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserService userService;
    private final StorageService storageService;

    public RentalsDTO getRentals() {
        return new RentalsDTO(rentalRepository.findAll());
    }

    public RentalsEntity findById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rental not found with id: " + id));
    }

    @Transactional
    public void createRental(RentalDTO rentalDTO) {
        rentalRepository.save(RentalsEntity.builder()
                .name(rentalDTO.getName())
                .surface(rentalDTO.getSurface())
                .price(rentalDTO.getPrice())
                .description(rentalDTO.getDescription())
                .ownerId(userService.findByEmail(userService.getCurrentAuthenticatedUserName()).getId())
                .picture(storageService.uploadPicture(rentalDTO.getPicture()))
                .createdAt(Timestamp.from(Instant.now()))
                .build());
    }

    @Transactional
    public void updateRental(Long id, RentalDTO rentalDTO) {
        RentalsEntity rentalToUpdate = findById(id);
        rentalToUpdate.setName(rentalDTO.getName());
        rentalToUpdate.setSurface(rentalDTO.getSurface());
        rentalToUpdate.setPrice(rentalDTO.getPrice());
        rentalToUpdate.setDescription(rentalDTO.getDescription());
        rentalToUpdate.setUpdatedAt(Timestamp.from(Instant.now()));
        rentalRepository.save(rentalToUpdate);
    }
}
