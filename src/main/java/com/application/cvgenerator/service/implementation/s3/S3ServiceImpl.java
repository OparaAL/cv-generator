package com.application.cvgenerator.service.implementation.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.application.cvgenerator.service.interfaces.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.endpoint}")
    private String endpoint;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private final String folder = "/cv";

    private final AmazonS3 s3client;

    @Override
    public String upload(Map<String, byte[]> file) throws IOException {
        if(file == null || file.isEmpty()) throw new IOException();
        Optional<String> filename = file.keySet().stream().findFirst();
        String fileUrl = "";
        try {
            fileUrl = endpoint + folder  + "/" + filename.get();
            uploadCvTos3bucket(filename.get(), file.get(filename.get()), folder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    @Override
    public void delete(String filename) {
        s3client.deleteObject(bucketName + folder, filename);
    }

    private void uploadCvTos3bucket(String fileName, byte[] file, String folder) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.length);
        metadata.setContentType("multipart/form-data");
        s3client.putObject(new PutObjectRequest(bucketName + folder, fileName, new ByteArrayInputStream(file), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}
