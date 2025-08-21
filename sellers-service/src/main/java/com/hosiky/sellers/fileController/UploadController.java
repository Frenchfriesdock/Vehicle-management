package com.hosiky.sellers.fileController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/path")
public class UploadController {

    @Autowired
    private ResourceLoader resourceLoader;


    private BufferedOutputStream bufferedOutputStream = null;

    @PostMapping("/uploadFileToServer")
    public Map<String, Object> UploadFileToServer(@RequestPart("files") MultipartFile[] files) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> fileResults = new ArrayList<>();

        // 使用绝对路径更可靠
//        String uploadDir = System.getProperty("user.dir") + "/back/scgkpt/src/main/resources/static/";
        String uploadDir = "F:/develop/hosiky/springcloud/vehicle_management/sellers-service/src/main/resources/static/";


        // 确保目录存在
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            result.put("success", false);
            result.put("message", "创建上传目录失败: " + e.getMessage());
            return result;
        }

        boolean allSuccess = true;
        for (MultipartFile file : files) {
            Map<String, String> fileResult = new HashMap<>();
            fileResult.put("filename", file.getOriginalFilename());

            try {
                String saveResult = saveFile(file, uploadDir);
                fileResult.put("status", "success");
                fileResult.put("message", saveResult);
            } catch (Exception e) {
                fileResult.put("status", "failed");
                fileResult.put("message", e.getMessage());
                allSuccess = false;
            }

            fileResults.add(fileResult);
        }

        result.put("success", allSuccess);
        result.put("results", fileResults);
        return result;
    }



    private String saveFile(MultipartFile file, String uploadDir) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("文件为空");
        }

        String filename = file.getOriginalFilename();
        if (filename == null || filename.trim().isEmpty()) {
            throw new IOException("无法获取文件名");
        }

        // 解决重名
        Path filePath = Paths.get(uploadDir, filename);
        if (Files.exists(filePath)) {
            String newFilename = filename.substring(0, filename.lastIndexOf('.'))
                    + "_" + System.currentTimeMillis()
                    + filename.substring(filename.lastIndexOf('.'));
            filePath = Paths.get(uploadDir, newFilename);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return "文件保存成功: " + filePath.getFileName();
    }

}
