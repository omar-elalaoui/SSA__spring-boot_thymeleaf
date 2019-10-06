package com.ssa.service;

import com.ssa.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    public Document convertFileToDocument(MultipartFile file) throws IOException;
}
