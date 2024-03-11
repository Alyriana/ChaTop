package com.openclassrooms.nja.chatop.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.openclassrooms.nja.chatop.exception.UploadFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadPicture(MultipartFile picture) throws IOException {
        try {
            return cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap()).get("secure_url").toString();
        } catch (Exception e) {
            throw new UploadFailureException("Upload failed. " + e.getMessage());
        }
    }
}