package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.response.RentalsDTO;
import com.openclassrooms.nja.chatop.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping("")
    public ResponseEntity<RentalsDTO> getAll() {
        RentalsDTO rentalsDTO = rentalService.getRentals();
        return ResponseEntity.ok(rentalsDTO);
    }
}
