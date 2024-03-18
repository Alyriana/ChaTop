package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.MessageDTO;
import com.openclassrooms.nja.chatop.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


/**
 * The MessageController class is a Rest Controller that handles HTTP requests related to messages.
 * The class is responsible for creating new messages.
 */
@RestController// Marks this class as a Rest Controller, making it a request handler
@RequiredArgsConstructor
// Lombok's annotation to automatically generate a constructor with required arguments (final fields)
@RequestMapping("/api") // Maps all HTTP requests starting with "/api" to this controller
public class MessageController {

    private final MessageService messageService; // Injects the MessageService to use its functionalities

    // Operation annotation to document the create message endpoint in OpenAPI
    @Operation(summary = "Create message", description = "Create a message for a rental.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Message created.",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", description = "Message, rental or user cannot be empty. BAD_REQUEST", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid user. UNAUTHORIZED", content = @Content)
            })
    @PostMapping("/messages") // Maps POST requests to /api/messages to this method
    public ResponseEntity<?> createRental(@Valid @RequestBody MessageDTO messageDTO) {
        // Calls the messageService to create a new message based on the passed DTO
        messageService.createMessage(messageDTO);
        // Returns a successful response entity with a confirmation message
        return ResponseEntity.ok(Collections.singletonMap("message", "Message send with success !"));
    }
}
