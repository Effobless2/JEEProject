package com.esgi.group5.jeeproject.infrastructure.persistence.azure;

import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Service
public class AzureImageUploaderService implements ImageUploadService {

    @Autowired
    private AzureBlobAdapter azureBlobAdapter;

    private URI uploadFileToAzure(MultipartFile multipartFile, Long id, String model) throws IOException {

        String newImageName = FilenameUtils.getBaseName(model + id) + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        MultipartFile newMultipartFile = new MockMultipartFile(
                newImageName,
                newImageName,
                multipartFile.getContentType(),
                multipartFile.getInputStream());

        return azureBlobAdapter.upload(newMultipartFile);
    }

    private Optional<String> uploadImageToStorage(MultipartFile multipartFile, Long id, String model) {
        try {
            return Optional.of(this.uploadFileToAzure(multipartFile, id, model).toString());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> uploadImageBeerImage(MultipartFile image, Long id) {
        return uploadImageToStorage(image, id, "beer");
    }

    @Override
    public Optional<String> uploadImageTradeImage(MultipartFile image, Long id) {
        return uploadImageToStorage(image, id, "trade");
    }
}