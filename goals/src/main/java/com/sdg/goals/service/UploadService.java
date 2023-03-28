package com.sdg.goals.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void uploadData(MultipartFile file, String fileType) throws Exception;
    boolean hasExcelFormat1(MultipartFile file);
}