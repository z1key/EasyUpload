package easyupload.repository;

import easyupload.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("select File f from files")
    Set<File> findByFolderAndToken(String folderName, String token);

    @Query("select File f from files")
    File findByFolderAndFilenameAndToken(String folderName, String filename, String token);
}
