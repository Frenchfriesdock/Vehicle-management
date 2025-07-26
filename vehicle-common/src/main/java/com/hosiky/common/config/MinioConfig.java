package com.hosiky.common.config;

import com.hosiky.common.client.MyMinioClient;
import com.hosiky.common.properties.MinioProperties;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   // 告诉 Spring 这是一个配置类
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties props) {
        return MinioClient.builder()
                .endpoint(props.getEndpoint())
                .credentials(props.getAccessKey(), props.getSecretKey())
                .build();
    }

    @Bean
    public MyMinioClient myMinioClient(MinioClient minioClient,
                                       MinioProperties props) {
        return new MyMinioClient(minioClient, props);
    }
}