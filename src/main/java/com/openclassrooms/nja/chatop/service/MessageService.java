package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.MessageDTO;
import com.openclassrooms.nja.chatop.entity.MessagesEntity;
import com.openclassrooms.nja.chatop.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserService userService;
    private final MessageRepository messageRepository;

    @Transactional
    public void createMessage(MessageDTO messageDTO) {
        messageRepository.save(MessagesEntity.builder()
                .rentalId(messageDTO.getRentalId())
                .userId(userService.findByEmail(userService.getCurrentAuthenticatedUserName()).getId())
                .message(messageDTO.getMessage())
                .createdAt(Timestamp.from(Instant.now()))
                .build());
    }
}