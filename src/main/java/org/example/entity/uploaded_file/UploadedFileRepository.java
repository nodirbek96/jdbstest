package org.example.entity.uploaded_file;

import java.sql.Connection;
import java.util.List;

public class UploadedFileRepository implements UploadFileCallbacks{
    private Connection connection;
    @Override
    public boolean createUploadsTable() {

        return false;
    }
    @Override
    public UploadedFile insertFile(UploadedFile uploadedFile) {
        return null;
    }

    @Override
    public UploadedFile updatedFile(UploadedFile uploadedFile) {
        return null;
    }

    @Override
    public UploadedFile getFileById(Integer id) {
        return null;
    }

    @Override
    public List<UploadedFile> getAll() {
        return null;
    }
}
