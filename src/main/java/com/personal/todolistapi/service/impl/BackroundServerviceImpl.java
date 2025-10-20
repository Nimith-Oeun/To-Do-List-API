package com.personal.todolistapi.service.impl;

import com.personal.todolistapi.exceptions.InternalServerError;
import com.personal.todolistapi.model.Background;
import com.personal.todolistapi.repository.BackroundRepository;
import com.personal.todolistapi.service.BackroundServervice;
import com.personal.todolistapi.utils.FileExtencion;
import com.personal.todolistapi.utils.FileHandle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BackroundServerviceImpl implements BackroundServervice {

    private final BackroundRepository backroundRepository;
    private final FileHandle fileHandle;
    private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/upload/";


    @Override
    public void uploadBackground(MultipartFile file) {
        fileHandle.validateUploadFile(file);
        fileHandle.validateFilePhotoFormat(file);


        try {
            Background filebackground = new Background();

            var name = FilenameUtils.removeExtension(file.getOriginalFilename());
            var extensionName = FileExtencion.getExtension(file.getOriginalFilename());
            var fileName = name + "." + extensionName;

            File filePathTemp = new File(FILE_UPLOAD_PATH + fileName);
            file.transferTo(filePathTemp);

            filebackground.setFileFomate(extensionName);
            filebackground.setFileSize(file.getSize());
            filebackground.setFileType("BACKGROUND-IMAGE");
            filebackground.setFileName(name);
            filebackground.setPartUpload(FILE_UPLOAD_PATH + fileName);

            backroundRepository.save(filebackground);

        } catch (IOException e) {

            log.error("Error while uploading file: {}", e.getMessage());
            throw new InternalServerError("Error while uploading file: " + e.getMessage());
        }
    }

    @Override
    public Background getfileById(Long id) {
        return backroundRepository.findById(id)
                .orElseThrow(() -> new InternalServerError("File not found with id: " + id));
    }

    @Override
    public List<Background> getAllfile() {
        return backroundRepository.findAll();
    }

    @Override
    public ResponseEntity<Resource> viewBackground(Long id) {

        Background file = getfileById(id);

        Path path = Paths.get(file.getPartUpload());

        try {
            if (!Files.exists(path)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Detect content type
            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            // If image → show inline, if other file → download
            ContentDisposition contentDisposition =
                    contentType.startsWith("image") ?
                            ContentDisposition.inline().filename(file.getFileName() + "." + file.getFileFomate()).build() :
                            ContentDisposition.attachment().filename(file.getFileName() + "." + file.getFileFomate()).build();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(Files.size(path))
                    .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES).cachePublic()) // <-- add this for caching images
                    .body(resource);

        } catch (IOException e) {
            log.error("Error while reading file: {}", e.getMessage());
            throw new InternalServerError("Error while reading file: " + e.getMessage());
        }
    }
}
