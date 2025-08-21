package com.hosiky.sellers.fileController;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@Slf4j
@RequestMapping("/path")

public class DownloadController {
    /**
     * 文件下载 isOnline默认为false
     */
    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response, boolean isOnLine) throws IOException {
        // 路径可以指定当前项目相对路径
        File file = new File("F:/develop/hosiky/springcloud/vehicle_management/sellers-service/src/main/resources/downLoad/" + fileName);
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();

            // 获取文件扩展名
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            if (!isOnLine) {
                // 根据文件扩展名设置Content-Type
                String contentType = getContentType(extension);
                response.setContentType(contentType);

                // 如果文件名为中文需要设置编码
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf8"));
                // 返回前端文件名需要添加
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            }
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }
    }

    // 根据文件扩展名获取Content-Type
    private String getContentType(String extension) {
        if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
            return "image/jpeg";
        } else if ("png".equalsIgnoreCase(extension)) {
            return "image/png";
        } else if ("gif".equalsIgnoreCase(extension)) {
            return "image/gif";
        } else if ("txt".equalsIgnoreCase(extension)) {
            return "text/plain";
        } else if ("pdf".equalsIgnoreCase(extension)) {
            return "application/pdf";
        } else if ("doc".equalsIgnoreCase(extension) || "docx".equalsIgnoreCase(extension)) {
            return "application/msword";
        } else if ("xls".equalsIgnoreCase(extension) || "xlsx".equalsIgnoreCase(extension)) {
            return "application/vnd.ms-excel";
        } else if ("ppt".equalsIgnoreCase(extension) || "pptx".equalsIgnoreCase(extension)) {
            return "application/vnd.ms-powerpoint";
        } else if ("zip".equalsIgnoreCase(extension)) {
            return "application/zip";
        } else if ("tar".equalsIgnoreCase(extension)) {
            return "application/x-tar";
        } else if ("rar".equalsIgnoreCase(extension)) {
            return "application/x-rar-compressed";
        } else if ("gz".equalsIgnoreCase(extension)) {
            return "application/gzip";
        } else {
            return "application/octet-stream";
        }
    }
}
