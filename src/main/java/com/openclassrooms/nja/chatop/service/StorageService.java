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

/**
 * StorageService is responsible for handling file storage operations in the application.
 * It provides methods for uploading pictures and generating URLs for accessing them.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class StorageService {
    // Defines the directory where images will be stored.
    private final Path imagesDirectory = Paths.get("src/main/resources/images");

    // Retrieves the base URL from the application properties, which is used for constructing the return URL of uploaded files.
    @Value("${app.baseUrl}")
    private String baseUrl;

    // Uploads a picture and returns the URL where it can be accessed.
    public String uploadPicture(MultipartFile picture) {
        validatePicture(picture); // Validates the picture before proceeding.
        try {
            createDirectoryIfNotExists(imagesDirectory); // Ensures the target directory exists.
            String fileName = generateFileName(picture); // Generates a safe, unique file name.
            Path targetLocation = imagesDirectory.resolve(fileName); // Resolves the file's target location.
            Files.copy(picture.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING); // Copies the file to the target location.
            return baseUrl + "/images/" + fileName; // Returns the URL of the uploaded file.
        } catch (IOException e) {
            throw new UploadFailureException("Picture upload failed: " + e.getMessage()); // Handles file I/O errors.
        }
    }

    // Validates the uploaded picture to ensure it's not null or empty.
    private void validatePicture(MultipartFile picture) {
        if (picture == null || picture.isEmpty()) {
            throw new BadRequestException("No picture provided");
        }
    }

    // Creates the directory for storing pictures if it does not already exist.
    private void createDirectoryIfNotExists(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    // Generates a unique file name for the uploaded picture to avoid naming conflicts and ensure security.
    private String generateFileName(MultipartFile picture) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
        if (originalFileName.contains("..")) {
            throw new BadRequestException("Invalid file path"); // Prevents directory traversal attacks.
        }

        // Extracts file name and extension to construct a new unique file name.
        String fileNameWithoutExtension = originalFileName;
        String fileExtension = "";

        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            fileNameWithoutExtension = originalFileName.substring(0, dotIndex);
            fileExtension = originalFileName.substring(dotIndex);
        }

        // Generates a random alphanumeric string to prepend to the file name for uniqueness.
        return RandomStringUtils.randomAlphanumeric(8) + "_" + fileNameWithoutExtension + fileExtension;
    }
}
