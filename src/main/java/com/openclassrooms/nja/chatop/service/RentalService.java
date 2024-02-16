package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.RentalDTO;
import com.openclassrooms.nja.chatop.dto.response.RentalsDTO;
import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.exception.CreationFailureException;
import com.openclassrooms.nja.chatop.exception.NotFoundException;
import com.openclassrooms.nja.chatop.repository.MessageRepository;
import com.openclassrooms.nja.chatop.repository.RentalRepository;
import com.openclassrooms.nja.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private StorageService storageService;

    public RentalsDTO getRentals() {
        return new RentalsDTO(rentalRepository.findAll());
    }

    public Optional<RentalsEntity> findById(Long id) {
        return rentalRepository.findById(id);
    }

    public RentalsEntity createRental(RentalDTO rental, UsersEntity user) {
        RentalsEntity newRental = new RentalsEntity();

        newRental.setName(rental.getName());
        newRental.setSurface(rental.getSurface());
        newRental.setPrice(rental.getPrice());
        newRental.setDescription(rental.getDescription());
        newRental.setOwnerId(user.getId());
        newRental.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            String filename = storageService.uploadPicture(rental);
            newRental.setPicture(filename);
            return rentalRepository.save(newRental);
        } catch (CreationFailureException | IOException e) {
            throw new CreationFailureException("Registration Failed");
        }
    }

    public RentalsEntity updateRental(RentalsEntity rental) {
        RentalsEntity updatedRental = findById((long) rental.getId())
                .orElseThrow(() -> new NotFoundException("Rental not found"));

        updatedRental.setName(rental.getName());
        updatedRental.setSurface(rental.getSurface());
        updatedRental.setPrice(rental.getPrice());
        updatedRental.setPicture(rental.getPicture());
        updatedRental.setDescription(rental.getDescription());
        updatedRental.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return rentalRepository.save(rental);
    }
}
