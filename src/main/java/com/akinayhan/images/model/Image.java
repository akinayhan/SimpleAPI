package com.akinayhan.images.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private String imageId;
    private String fileName;
    private String fileType;
    private byte[] data;
}