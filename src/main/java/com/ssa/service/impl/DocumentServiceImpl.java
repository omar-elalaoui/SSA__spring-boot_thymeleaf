package com.ssa.service.impl;

import com.ssa.entity.Document;
import com.ssa.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public Document convertFileToDocument(MultipartFile file) {
        return null;
    }
}
