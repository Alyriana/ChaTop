package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.MessageDTO;
import com.openclassrooms.nja.chatop.entity.MessagesEntity;
import com.openclassrooms.nja.chatop.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * The MessageService class provides methods to manage messages.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class MessageService {

    // Auto-injects UserService and MessageRepository dependencies.
    private final UserService userService;
    private final MessageRepository messageRepository;

    // Annotates this method to be executed within a transactional context. This ensures that
    // the entire method executes within a single transaction and rolls back if any exception occurs.
    @Transactional
    public void createMessage(MessageDTO messageDTO) {
        // Saves a new message entity to the repository. The builder pattern is used to create
        // the MessagesEntity instance. It sets the rentalId and message from the DTO,
        // the userId by looking up the currently authenticated user, and sets the current timestamp as the creation time.
        messageRepository.save(MessagesEntity.builder()
                .rentalId(messageDTO.getRentalId()) // Sets the rental ID from the DTO.
                // Finds the currently authenticated user by their email and gets their ID.
                .userId(userService.findByEmail(userService.getCurrentAuthenticatedUserName()).getId())
                .message(messageDTO.getMessage()) // Sets the message content from the DTO.
                .createdAt(Timestamp.from(Instant.now())) // Sets the current timestamp as the creation time.
                .build());
    }
}
