package com.esgi.group5.jeeproject.domain.tools;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImageUploadService {
    Optional<String> uploadImageBeerImage(MultipartFile image, Long id);
    Optional<String> uploadImageTradeImage(MultipartFile image, Long id);
}
