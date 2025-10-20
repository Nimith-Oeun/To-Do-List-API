package com.personal.todolistapi.controller;

import com.personal.todolistapi.exceptions.CustomMaxUploadSizeExceededException;
import com.personal.todolistapi.exceptions.SuccessRespone;
import com.personal.todolistapi.model.Background;
import com.personal.todolistapi.service.BackroundServervice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/backgrounds")
@RequiredArgsConstructor
@Slf4j
public class BackgroundController {

    private final BackroundServervice backroundServervice;

    @PreAuthorize( "hasRole('role_admin')")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadBackground(@RequestParam("file") MultipartFile file) {

        try{

            log.info("Uploading background photo" + file.getOriginalFilename());

            //handle file size
            if (file.getSize() > 1024 * 1024) { // 5 MB limit
                throw new CustomMaxUploadSizeExceededException("File size exceeds the maximum limit of 5 MB");
            }

            backroundServervice.uploadBackground(file);
            return ResponseEntity.ok(SuccessRespone.success("File uploaded successfully"));

        }catch (Exception e){

            log.info("Error occurred while uploading photo: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of(
                    "status",
                    HttpStatus.BAD_REQUEST,
                    "File to upload file",
                    e.getMessage()));

        }
    }


    @PreAuthorize( "hasRole('role_user') or hasRole('role_admin')")
    @GetMapping("/getAllBackgrounds")
    public ResponseEntity<List<Background>> getAllBackgrounds() {
        return ResponseEntity.ok(backroundServervice.getAllfile());
    }

    @PreAuthorize( "hasRole('role_user') or hasRole('role_admin')")
    @GetMapping("/view")
    public ResponseEntity<?> viewPhoto(@PathVariable("id") Long id) {
        return backroundServervice.viewBackground(id);
    }

}
