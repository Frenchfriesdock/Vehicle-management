package com.hosiky.sellers.config;

import com.hosiky.common.config.MinioConfig;
import com.hosiky.common.properties.MinioProperties;
import com.hosiky.common.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
    public class InitConfig implements InitializingBean {
 
    @Autowired
    private MinioUtils minioUtils;
 
 
    @Autowired
    private MinioProperties minioProperties;
 
    @Override
    public void afterPropertiesSet() throws Exception {
        // 项目启动创建Bucket，不存在则进行创建
        minioUtils.createBucket(minioProperties.getBucketName());
    }
}