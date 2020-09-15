package com.bestpractices.spamdetector.service.implementation;

import com.bestpractices.spamdetector.entity.FileScanResponse;
import com.bestpractices.spamdetector.service.FileScanService;
import fi.solita.clamav.ClamAVClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileScanServiceClam implements FileScanService {

    private final ClamAVClient clamAVClient;

    public FileScanServiceClam(){
        this.clamAVClient = new ClamAVClient("scan", 3310);
    }

    @Override
    public List<FileScanResponse> scanFiles(MultipartFile[] files) {
        return Arrays.stream(files).map(multipartFile -> {
            FileScanResponse fileScanResponse = new FileScanResponse();
            long startTime = System.currentTimeMillis();
            fileScanResponse.setUploadTime(startTime);
            try {
                byte[] response = this.clamAVClient.scan(multipartFile.getInputStream());
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
