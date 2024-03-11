package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.request.RentalDTO;
import com.openclassrooms.nja.chatop.dto.response.RentalsDTO;
import com.openclassrooms.nja.chatop.dto.response.ResponseDTO;
import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import com.openclassrooms.nja.chatop.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping("")
    public ResponseEntity<RentalsDTO> getAll() {
        RentalsDTO rentalsDTO = rentalService.getRentals();
        return ResponseEntity.ok(rentalsDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> createRental(RentalDTO rentalDTO) {
        return ResponseEntity.ok(rentalService.createRental(rentalDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getRentalById(@PathVariable("id") Long id) {
        RentalsEntity rental = rentalService.findById(id);
        if (rental != null) {
            return ResponseEntity.ok(rental);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("Rental not found")
                            .errors(Collections.singletonList("No rental found with the provided ID"))
                            .build());
        }
    }
}
