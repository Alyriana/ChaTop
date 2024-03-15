package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.MessageDTO;
import com.openclassrooms.nja.chatop.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createRental(MessageDTO messageDTO) {
        messageService.createMessage(messageDTO);
        return ResponseEntity.ok(Collections.singletonMap("message", "Message send with success !"));
    }

}
