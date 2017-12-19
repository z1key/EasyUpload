package easyupload.service;

import easyupload.entity.File;
import easyupload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class FileDownloadService {

    @Autowired
    FileRepository fileRepository;

    // Retrieve file
    public Set<File> findByFolder(String folderName, String token) {
        return fileRepository.findByFolderAndToken(folderName, token);
    }

    // Retrieve file
    public File findByFilename(String folderName, String fileName, String token) {
        return fileRepository.findByFolderAndFilenameAndToken(folderName, fileName, token);
    }
}
