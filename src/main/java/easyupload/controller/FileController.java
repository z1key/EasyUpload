package easyupload.controller;

import easyupload.entity.File;
import easyupload.service.FileDownloadService;
import easyupload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipFile;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    FileDownloadService fileDownloadService;

    // Download a file
    @RequestMapping(
            value = "/download",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> downloadFile(
            @RequestParam("folderName") String folderName,
            @RequestParam("token") String token
    ) {

        Set<File> files = fileDownloadService.findByFolder(folderName, token);

        // No file found based on the supplied filename
        if (files.isEmpty()) {
            return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
        }

        // Generate the http headers with the file properties
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filenames=" + files.toString());

        // Split the mimeType into primary and sub types
        String primaryType, subType;
        for (File file : files) {
            try {
                primaryType = file.getMimeType().split("/")[0];
                subType = file.getMimeType().split("/")[1];
            } catch (IndexOutOfBoundsException | NullPointerException ex) {
                return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        //TODO Zipping

//        headers.setContentType( new MediaType(primaryType, subType) );
          return new ResponseEntity<>(null, headers, HttpStatus.OK);
//        return new ResponseEntity<>(files.getFile(), headers, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST
    )
    public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String mimeType = file.getContentType();
                String filename = file.getOriginalFilename();
                byte[] bytes = file.getBytes();

                File newFile = new File(filename, bytes, mimeType);

                fileUploadService.uploadFile(newFile);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
