package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    @NotBlank(message = "Message cannot be empty")
    private String message;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer rentalId;
}