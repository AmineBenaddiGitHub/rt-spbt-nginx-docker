package com.bestpractices.spamdetector.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileScanResponse {
    private String fileName;
    private Boolean detected;
    private long size;
    private long scanTimeInMilliSec;
    private String errorMessage;
    private String hash;
    private long uploadTime;
}
