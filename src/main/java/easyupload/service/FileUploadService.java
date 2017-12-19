package easyupload.service;

import easyupload.entity.File;
import easyupload.entity.Folder;
import easyupload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService {

    @Autowired
    FileRepository fileRepository;

    // Upload the file
    public void uploadFile(File file) {
        fileRepository.saveAndFlush(file);
    }
}
