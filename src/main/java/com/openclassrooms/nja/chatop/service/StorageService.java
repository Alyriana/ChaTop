package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.exception.BadRequestException;
import com.openclassrooms.nja.chatop.exception.UploadFailureException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
@Service
public class StorageService {
    private final Path imagesDirectory = Paths.get("src/main/resources/images");
    @Value("${app.baseUrl}")
    private String baseUrl;


    public String uploadPicture(MultipartFile picture) {
        if (picture == null || picture.isEmpty()) {
            throw new BadRequestException("No picture provided");
        }
        try {
            if (!Files.exists(imagesDirectory)) {
                Files.createDirectories(imagesDirectory);
            }
            String originalFileName = picture.getOriginalFilename();
            String cleanedFileName = originalFileName.replaceAll("[^a-zA-Z0-9._-]", "_");
            String fileExtension = "";
            if (cleanedFileName != null && cleanedFileName.contains(".")) {
                fileExtension = cleanedFileName.substring(cleanedFileName.lastIndexOf("."));
                cleanedFileName = cleanedFileName.substring(0, cleanedFileName.lastIndexOf("."));
            }
            String fileName = RandomStringUtils.randomAlphanumeric(8) + "_" + cleanedFileName + fileExtension;
            Path targetLocation = imagesDirectory.resolve(fileName);
            Files.copy(picture.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return baseUrl + "/images/" + fileName;
        } catch (Exception e) {
            throw new UploadFailureException("Picture upload failed: " + e.getMessage(), e);
        }
    }
}