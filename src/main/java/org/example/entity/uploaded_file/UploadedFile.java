package org.example.entity.uploaded_file;

import java.time.LocalDateTime;

public class UploadedFile {
    private Integer id;
    private Integer candidateId;
    private String fileUrl;
    private byte[] file;
    private Integer size;
    private LocalDateTime createdDate;
}
