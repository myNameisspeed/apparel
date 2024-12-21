package com.example.demo.apparel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {
    private String fileUuid;
    private String mimeType;
    private Integer  isImageOrVideo;
    private Integer  entityId;
    private String  fileSize;
    private Integer  isDeleted;
}
