package com.openclassrooms.nja.chatop.dto.response;

import com.openclassrooms.nja.chatop.entity.RentalsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalsDTO {
    private List<RentalsEntity> rentals;
}
