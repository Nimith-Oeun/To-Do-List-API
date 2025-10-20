package com.personal.todolistapi.service;

import com.personal.todolistapi.model.Background;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BackroundServervice {

    void uploadBackground(MultipartFile file);

    Background getfileById(Long id);

    List<Background> getAllfile();

    ResponseEntity<Resource> viewBackground(Long id);
}
