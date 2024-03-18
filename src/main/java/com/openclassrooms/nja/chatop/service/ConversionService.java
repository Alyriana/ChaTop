package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.MessageConversionDTO;
import com.openclassrooms.nja.chatop.dto.RentalConversionDTO;
import com.openclassrooms.nja.chatop.dto.UserConversionDTO;
import com.openclassrooms.nja.chatop.entity.MessagesEntity;
import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The ConversionService class provides methods to convert entity objects to corresponding DTO objects.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class ConversionService {

    // Converts a RentalsEntity to its corresponding DTO format.
    public RentalConversionDTO rentalToDTO(RentalsEntity rental) {
        RentalConversionDTO rentalDTO = new RentalConversionDTO();
        // Manually sets each property from the entity to the DTO.
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPicture(rental.getPicture());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwnerId(rental.getOwnerId());
        // Converts Timestamp to LocalDateTime for the DTO.
        rentalDTO.setCreatedAt(rental.getCreatedAt().toLocalDateTime());
        if (rental.getUpdatedAt() != null) {
            rentalDTO.setUpdatedAt(rental.getUpdatedAt().toLocalDateTime());
        }
        return rentalDTO;
    }

    // Converts a MessagesEntity to its corresponding DTO format.
    public MessageConversionDTO messageToDTO(MessagesEntity message) {
        MessageConversionDTO messageDTO = new MessageConversionDTO();
        // Sets properties from the entity to the DTO, including time conversions.
        messageDTO.setId(message.getId());
        messageDTO.setRentalId(message.getRentalId());
        messageDTO.setUserId(message.getUserId());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setCreatedAt(message.getCreatedAt().toLocalDateTime());
        if (message.getUpdatedAt() != null) {
            messageDTO.setUpdatedAt(message.getUpdatedAt().toLocalDateTime());
        }
        return messageDTO;
    }

    // Converts a UsersEntity to its corresponding DTO format.
    public UserConversionDTO userToDTO(UsersEntity user) {
        UserConversionDTO userDTO = new UserConversionDTO();
        // Populates the DTO with user entity data, handling nulls for update times.
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setCreatedAt(user.getCreatedAt().toLocalDateTime());
        if (user.getUpdatedAt() != null) {
            userDTO.setUpdatedAt(user.getUpdatedAt().toLocalDateTime());
        }
        return userDTO;
    }

}
