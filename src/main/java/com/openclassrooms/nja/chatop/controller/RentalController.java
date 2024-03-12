package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.request.RentalDTO;
import com.openclassrooms.nja.chatop.dto.response.RentalsDTO;
import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import com.openclassrooms.nja.chatop.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(rentalService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateRental(@PathVariable("id") Long id, RentalsEntity rental) {
        return ResponseEntity.ok(rentalService.updateRental(id, rental));
    }
}