package com.esgi.group5.jeeproject.services.contracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

public interface IAzureService{
    URI uploadImageToStorage(MultipartFile multipartFile, Long id, String model) throws IOException;
}