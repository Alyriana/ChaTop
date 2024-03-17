package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Surface cannot be empty")
    private Integer surface;

    @NotNull(message = "Price cannot be empty")
    private Integer price;

    //@NotBlank(message = "Picture cannot be empty")
    private MultipartFile picture;

    @NotNull(message = "Description cannot be empty")
    @Size(max = 2000, message = "Description length must be less than 2000 characters.")
    private String description;

}
