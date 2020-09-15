package com.bestpractices.spamdetector.service;

import com.bestpractices.spamdetector.entity.FileScanResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileScanService {
    List<FileScanResponse> scanFiles(MultipartFile[] files);
}
