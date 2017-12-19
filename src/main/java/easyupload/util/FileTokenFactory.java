package easyupload.util;

import easyupload.entity.Folder;
import easyupload.entity.User;

import java.util.UUID;

public class FileTokenFactory {

    public static FileToken generate(User user) {
        Folder folder = new Folder();
        folder.setRoot(true);
        return new FileToken(folder,
                             generateHash(),
                             user);
    }

    private static String generateHash() {
        return UUID.randomUUID().toString();
    }
}
