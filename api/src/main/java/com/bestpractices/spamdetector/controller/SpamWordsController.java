package com.bestpractices.spamdetector.controller;

import com.bestpractices.spamdetector.entity.FileScanResponse;
import com.bestpractices.spamdetector.entity.ScanResponse;
import com.bestpractices.spamdetector.entity.SpamWord;
import com.bestpractices.spamdetector.service.FileScanService;
import com.bestpractices.spamdetector.service.SpamWordsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SpamWordsController {

    @Autowired
    SpamWordsManager spamWordsManagerFile;

    @Autowired
    FileScanService fileScanServiceClam;

    @GetMapping("/spam-words")
    public List<SpamWord> returnSpamWordsFile(HttpServletRequest request) {
        System.out.println("0o0o0o0o0o0o");
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getHeader("My-Custom-Header"));
        System.out.println("0o0o0o0o0o0o");
        return spamWordsManagerFile.getSpamWords();
    }

    @PostMapping("/upload")
    public ScanResponse<List<FileScanResponse>> uploadFiles(@RequestParam("files") MultipartFile files) {
        return new ScanResponse<>(fileScanServiceClam.scanFiles(new MultipartFile[]{files}));
    }
}
