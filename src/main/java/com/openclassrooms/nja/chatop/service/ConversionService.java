package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.MessageConversionDTO;
import com.openclassrooms.nja.chatop.dto.RentalConversionDTO;
import com.openclassrooms.nja.chatop.dto.UserConversionDTO;
import com.openclassrooms.nja.chatop.entity.MessagesEntity;
import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConversionService {

    public RentalConversionDTO rentalToDTO(RentalsEntity rental) {
        RentalConversionDTO rentalDTO = new RentalConversionDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPicture(rental.getPicture());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwner_id(rental.getOwnerId());
        rentalDTO.setCreatedAt(rental.getCreatedAt().toLocalDateTime());
        if (rental.getUpdatedAt() != null) {
            rentalDTO.setUpdatedAt(rental.getUpdatedAt().toLocalDateTime());
        }
        return rentalDTO;
    }

    public MessageConversionDTO messageToDTO(MessagesEntity message) {
        MessageConversionDTO messageDTO = new MessageConversionDTO();
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

    public UserConversionDTO userToDTO(UsersEntity user) {
        UserConversionDTO userDTO = new UserConversionDTO();
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
