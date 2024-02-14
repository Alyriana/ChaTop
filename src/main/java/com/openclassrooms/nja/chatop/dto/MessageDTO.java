package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    @NotBlank(message = "Message cannot be empty")
    private String message;

    @NotBlank
    private Integer userId;

    @NotBlank
    private Integer rentalId;
}