package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.exception.BadRequestException;
import com.openclassrooms.nja.chatop.exception.UploadFailureException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StorageService {
    private final Path imagesDirectory = Paths.get("src/main/resources/images");

    @Value("${app.baseUrl}")
    private String baseUrl;

    public String uploadPicture(MultipartFile picture) {
        validatePicture(picture);
        try {
            createDirectoryIfNotExists(imagesDirectory);
            String fileName = generateFileName(picture);
            Path targetLocation = imagesDirectory.resolve(fileName);
            Files.copy(picture.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return baseUrl + "/images/" + fileName;
        } catch (IOException e) {
            throw new UploadFailureException("Picture upload failed: " + e.getMessage());
        }
    }

    private void validatePicture(MultipartFile picture) {
        if (picture == null || picture.isEmpty()) {
            throw new BadRequestException("No picture provided");
        }
    }

    private void createDirectoryIfNotExists(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    private String generateFileName(MultipartFile picture) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
        if (originalFileName.contains("..")) {
            throw new BadRequestException("Invalid file path");
        }

        String fileNameWithoutExtension = originalFileName;
        String fileExtension = "";

        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            fileNameWithoutExtension = originalFileName.substring(0, dotIndex);
            fileExtension = originalFileName.substring(dotIndex);
        }

        return RandomStringUtils.randomAlphanumeric(8) + "_" + fileNameWithoutExtension + fileExtension;
    }
}
