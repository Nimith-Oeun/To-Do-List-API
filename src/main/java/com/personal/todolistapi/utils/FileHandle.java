package com.personal.todolistapi.utils;

import com.personal.todolistapi.exceptions.BadRequestException;
import com.personal.todolistapi.exceptions.InternalServerError;
import com.personal.todolistapi.repository.BackroundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileHandle {

    private final BackroundRepository backgroundRepository;
    private final List<String> FILE_EXTENSIONS_CV = List.of("pdf");
    private final List<String> FILE_EXTENSIONS_PF = List.of("png","jpg","jpeg");

    //check if the file is empty
    public void validateUploadFile(MultipartFile files){
        if(files.isEmpty()){
            log.info("No file uploaded");
            throw new BadRequestException("No file uploaded");
        }
    }

    //check file format for CV
    public void validateFileCVFormat(MultipartFile files){

        var fileName = StringUtils.cleanPath(Objects.requireNonNull(files.getOriginalFilename()));
        var extension = FileExtencion.getExtension(fileName);

        if (!FILE_EXTENSIONS_CV.contains(extension)) {
            log.warn("File Extencion is not allow to upload , please verify: {}", fileName);
            throw new InternalServerError("File Extencion is not allow to upload , please verify: " + fileName);
        }
    }

    //check file format for Profile Photo
    public void validateFilePhotoFormat(MultipartFile files){

        var fileName = StringUtils.cleanPath(Objects.requireNonNull(files.getOriginalFilename()));
        var extension = FileExtencion.getExtension(fileName);

        if (!FILE_EXTENSIONS_PF.contains(extension)) {
            log.warn("File Extencion is not allow to upload , please verify: {}", fileName);
            throw new InternalServerError(
                    "File Extencion " + extension +"is not allow to upload , please verify: " + fileName
            );
        }
    }
}