package easyupload.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class File {

    public File(String filename, byte[] file, String mimeType) {

        this.file = file;
        this.filename = filename;
        this.mimeType = mimeType;
    }

    public File() {
        // Default Constructor
    }

    @Id
    private String filename;

    @Lob
    private byte[] file;

    private Folder folder;

    private String mimeType;

}
