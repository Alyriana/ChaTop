package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Surface cannot be empty")
    private Integer surface;

    @NotNull(message = "Price cannot be empty")
    private Integer price;

    //@NotBlank(message = "Picture cannot be empty")
    private MultipartFile picture;

    @NotBlank(message = "Description cannot be empty")
    private String description;

}