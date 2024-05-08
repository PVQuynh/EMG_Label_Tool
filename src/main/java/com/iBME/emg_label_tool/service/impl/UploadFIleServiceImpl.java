package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.InforFile;
import com.iBME.emg_label_tool.exception.AlreadyExistsException;
import com.iBME.emg_label_tool.service.UploadFIleService;
import com.iBME.emg_label_tool.utils.GetPreSignedUrlUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@Data
public class UploadFIleServiceImpl implements UploadFIleService {

    private final MinioClient minioClient;
    private final ApplicationEventPublisher publisher;
    private final GetPreSignedUrlUtils getPreSignedUrlUtils;

    String defaultBucketName = "emg-app";

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    @Override
    public InforFile saveAndGetFileLocation(MultipartFile file) {
        String name = file.getOriginalFilename();
        if(getAllFile().contains(name)) {
            throw new AlreadyExistsException();
        }

        try {
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

            return InforFile.builder()
                    .fileName(name)
                    .filePath(getPreSignedUrlUtils.getPreSignedUrl(url))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<String> getAllFile() {
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(defaultBucketName).build());

            List<String> filesName = new ArrayList<>();
            for (Result<Item> result : results) {
                Item item = result.get();
                String fileName = item.objectName();

                filesName.add(fileName);
            }

            return filesName;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
