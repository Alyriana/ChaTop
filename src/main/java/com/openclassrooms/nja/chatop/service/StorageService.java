package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.RentalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class StorageService {
    public String uploadPicture(RentalDTO rental) throws IOException {
        MultipartFile imageFile = rental.getPicture();
        String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path path = Paths.get("src/main/resources/static/images/", filename);
        Files.write(path, imageFile.getBytes());
        return "/images/" + filename;
    }
}