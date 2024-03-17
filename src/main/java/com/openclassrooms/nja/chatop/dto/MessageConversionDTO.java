package com.openclassrooms.nja.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageConversionDTO {

    private Integer id;

    @JsonProperty(value = "rental_id")
    private Integer rentalId;

    @JsonProperty(value = "user_id")
    private Integer userId;

    private String message;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;
}
