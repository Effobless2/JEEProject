package com.esgi.group5.jeeproject.persistence.azure;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class AzureBlobAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AzureBlobAdapter.class);

    @Autowired
    private CloudBlobClient cloudBlobClient;

    @Autowired
    private CloudBlobContainer cloudBlobContainer;

    public URI upload(MultipartFile multipartFile){
        URI uri = null;
        CloudBlockBlob blob = null;
        try {
            blob = cloudBlobContainer.getBlockBlobReference(multipartFile.getOriginalFilename());
            blob.upload(multipartFile.getInputStream(), -1);
            uri = blob.getUri();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }
}
