package com.openclassrooms.nja.chatop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;
}
