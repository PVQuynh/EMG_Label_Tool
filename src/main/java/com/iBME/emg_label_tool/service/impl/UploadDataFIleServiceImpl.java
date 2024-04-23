package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.service.UploadDataFIleService;
import com.iBME.emg_label_tool.utils.GetPreSignedUrlUtils;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Data
public class UploadDataFIleServiceImpl implements UploadDataFIleService {

    private final MinioClient minioClient;
    private final ApplicationEventPublisher publisher;
    private final GetPreSignedUrlUtils getPreSignedUrlUtils;

    String defaultBucketName = "hust-app";

    @Value("${minio.default.folder}")
    String defaultBaseFolder;
    @Override
    public String saveAndGetFileLocation(MultipartFile file) {
        try {
            String name = file.getOriginalFilename();
            byte[] content = file.getBytes();
            InputStream inputStream = new ByteArrayInputStream(content);

            // Tải lên tệp tin lên Minio sử dụng InputStream
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(defaultBucketName)
                            .object(defaultBaseFolder + name)
                            .stream(inputStream, content.length, -1) // Sử dụng InputStream
                            .build()
            );

            // Tạo pre-signed URL cho tệp tin vừa tải lên
            String url =
                    minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(defaultBucketName)
                                    .object(defaultBaseFolder + name)
                                    .expiry(7, TimeUnit.DAYS)
                                    .build());

            return getPreSignedUrlUtils.getPreSignedUrl(url);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
