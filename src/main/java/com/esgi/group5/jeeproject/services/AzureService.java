package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.Azure.AzureBlobAdapter;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import com.esgi.group5.jeeproject.services.contracts.IAzureService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class AzureService implements IAzureService {

    @Autowired
    private AzureBlobAdapter azureBlobAdapter;

    @Override
    public URI uploadImageToStorage(MultipartFile multipartFile, Long id, String model) throws IOException {

        String newImageName = FilenameUtils.getBaseName(model + id) + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        MultipartFile newMultipartFile = new MockMultipartFile(
                newImageName,
                newImageName,
                multipartFile.getContentType(),
                multipartFile.getInputStream());

        return azureBlobAdapter.upload(newMultipartFile);
    }

}