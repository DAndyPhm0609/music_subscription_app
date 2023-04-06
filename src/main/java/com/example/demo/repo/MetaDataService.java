package com.example.demo.repo;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.demo.model.Music;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Service
@Slf4j
public class MetaDataService {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    public void upload(MultipartFile file) throws IOException {

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        // Uploading file to s3
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        // Saving metadata to db
        //fileMetaRepository.save(new FileMeta(fileName, path, putObjectResult.getMetadata().getVersionId()));
    }

    public void upload_file(Music music) throws IOException {

        if (Objects.equals(music.getImage_url(), ""))
            throw new IllegalStateException("Cannot upload empty file");

        InputStream input = new URL(music.getImage_url()).openStream();
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", "audio/mpeg");
        metadata.put("Content-Length", String.valueOf(input.available()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", music.getTitle());

        // Uploading file to s3
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName, Optional.of(metadata), input);

        // Saving metadata to db
        //fileMetaRepository.save(new FileMeta(fileName, path, putObjectResult.getMetadata().getVersionId()));
    }

}