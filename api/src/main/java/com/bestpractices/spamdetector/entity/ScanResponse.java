package com.bestpractices.spamdetector.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScanResponse<T> {
    private T data;
    private Map<Object,Object> metaData;

    public ScanResponse(T data) {
        this.data = data;
    }
}
