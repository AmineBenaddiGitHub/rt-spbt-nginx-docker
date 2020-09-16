package com.bestpractices.spamdetector.service.implementation;

import com.bestpractices.spamdetector.entity.FileScanResponse;
import com.bestpractices.spamdetector.service.FileScanService;
import fi.solita.clamav.ClamAVClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileScanServiceClam implements FileScanService {

    @Override
    public List<FileScanResponse> scanFiles(MultipartFile[] files) throws IOException {
        ClamAVClient clamAVClient = new ClamAVClient("scan", 3310);
        if(!clamAVClient.ping())
            return Collections.emptyList();
        return Arrays.stream(files).map(multipartFile -> {
            FileScanResponse fileScanResponse = new FileScanResponse();
            long startTime = System.currentTimeMillis();
            fileScanResponse.setUploadTime(startTime);
            try {
                byte[] response = clamAVClient.scan(multipartFile.getInputStream());
                boolean status = ClamAVClient.isCleanReply(response);
                fileScanResponse.setDetected(!status);
                System.out.println("File Scanned = {} Clam AV Response = {} " + multipartFile.getOriginalFilename() + (status));
            } catch(Exception ex) {
                System.out.println("Exception occurred while scanning using clam av = {} " + ex.getMessage());
                fileScanResponse.setErrorMessage(ex.getMessage());
            }
            fileScanResponse.setFileName(multipartFile.getOriginalFilename());
            fileScanResponse.setSize(multipartFile.getSize());
            fileScanResponse.setScanTimeInMilliSec(System.currentTimeMillis() - startTime);
            return fileScanResponse;
        }).collect(Collectors.toList());
    }
}
