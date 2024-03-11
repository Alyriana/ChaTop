package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.request.RentalDTO;
import com.openclassrooms.nja.chatop.dto.response.RentalsDTO;
import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.exception.BadRequestException;
import com.openclassrooms.nja.chatop.exception.CreationFailureException;
import com.openclassrooms.nja.chatop.exception.NotFoundException;
import com.openclassrooms.nja.chatop.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    public RentalsDTO getRentals() {
        return new RentalsDTO(rentalRepository.findAll());
    }

    public RentalsEntity findById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rental not found with id: " + id));
    }

    public RentalsEntity createRental(RentalDTO rentalDTO) {
        RentalsEntity rental = new RentalsEntity();

        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        rental.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        UsersEntity user = userService.findByEmail(userService.getCurrentAuthenticatedUserName());

        rental.setOwnerId(user.getId());
        rental.setPicture(uploadPicture(rentalDTO.getPicture()));

        return rentalRepository.save(rental);
    }

    private String uploadPicture(MultipartFile picture) {
        if (picture == null || picture.isEmpty()) {
            throw new BadRequestException("No picture provided");
        }
        try {
            return cloudinaryService.uploadPicture(picture);
        } catch (IOException e) {
            throw new CreationFailureException("Picture upload failed: " + e.getMessage(), e);
        }
    }
}