package easyupload.util;

import easyupload.entity.Folder;
import easyupload.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FileToken {

    @Getter @Setter
    private Folder folder;

    @Getter
    private String hash;

    @Getter @Setter
    private User user;
}
