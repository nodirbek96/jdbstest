package org.example.entity.uploaded_file;

import java.util.List;

public interface UploadFileCallbacks {
    boolean createUploadsTable();
    UploadedFile insertFile(UploadedFile uploadedFile);
    UploadedFile updatedFile(UploadedFile uploadedFile);
    UploadedFile getFileById(Integer id);
    List<UploadedFile> getAll();

}
