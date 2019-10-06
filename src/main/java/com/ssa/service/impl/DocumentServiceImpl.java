package com.ssa.service.impl;

import com.ssa.entity.Document;
import com.ssa.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public Document convertFileToDocument(MultipartFile file) throws IOException {
        Document document= new Document();
        if(!file.isEmpty()){
            document.setNomDoc(file.getOriginalFilename());
            document.setDocData(file.getBytes());
            return document;
        }
        return null;
    }
}
