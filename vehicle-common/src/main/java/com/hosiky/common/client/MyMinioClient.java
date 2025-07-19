package com.hosiky.common.client;

import com.hosiky.common.properties.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class
MyMinioClient {

    private MinioClient minioClient;

    private final MinioProperties minioProperties;


    public String upload(MultipartFile file) throws FileUploadException {
        String fileName = generateFileName(file);
        try (InputStream inputStream = file.getInputStream()){
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(minioProperties.getBucketName())
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            log.error("Error occurred while uploading file to MinIO: {}", fileName, e);
            throw new FileUploadException("failed to upload file to minio" + fileName, e);
        }

        return String.format("%s/%s", minioProperties.getBaseUrl(), fileName);
    }

    private String generateFileName(MultipartFile file) {

        return UUID.randomUUID() + "-" + file.getOriginalFilename();
    }

}
