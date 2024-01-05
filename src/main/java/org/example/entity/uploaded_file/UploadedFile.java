package org.example.entity.uploaded_file;

import java.time.LocalDateTime;

public class UploadedFile {
    private Integer id;
    private Integer candidateId;
    private String name;
    private String extension;
    private String fileUrl;
    private String savedUrl;
    private Integer size;
    private LocalDateTime createdDate;
}
