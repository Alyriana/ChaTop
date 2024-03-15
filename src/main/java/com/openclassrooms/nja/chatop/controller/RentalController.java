package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.RentalDTO;
import com.openclassrooms.nja.chatop.dto.RentalsDTO;
import com.openclassrooms.nja.chatop.service.RentalService;
import lombok.RequiredArgsConstructor;
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
        rentalService.createRental(rentalDTO);
        return ResponseEntity.ok(Collections.singletonMap("message", "Rental created !"));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getRentalById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rentalService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateRental(@PathVariable("id") Long id, RentalDTO rentalDTO) {
        rentalService.updateRental(id, rentalDTO);
        return ResponseEntity.ok(Collections.singletonMap("message", "Rental updated !"));
    }

}