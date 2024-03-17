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
public class RentalConversionDTO {

    private Integer id;

    private String name;

    private Integer surface;

    private Integer price;

    private String picture;

    private String description;

    @JsonProperty(value = "owner_id")
    private Integer owner_id;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;

}
