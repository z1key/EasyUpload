package easyupload.entity;

import easyupload.util.FileToken;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Data
@Entity
public class Folder {

    @Id
    private long id;
    private FileToken token;
    private String name;
    private User user;
    private Set<File> files;
    private Set<Folder> folders;
    private boolean isRoot;
}
