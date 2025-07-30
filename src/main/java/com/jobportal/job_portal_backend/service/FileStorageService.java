package com.jobportal.job_portal_backend.Service;

import ch.qos.logback.core.util.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
    public FileStorageService(){
        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("could not craete ");
        }
    }
    public String storeFile(MultipartFile file){
        String fileName = UUID.randomUUID()+"_"+ StringUtils.cleanPath(file.getOriginalFilename());
        try{
            Path target= this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(),target, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (IOException ex){
            throw new RuntimeException("Faild to upload",ex);
        }
    }
    public Resource loadFile(String fileName){
        try{
            Path filePath= fileStorageLocation.resolve(fileName).normalize();
            UrlResource resource =new UrlResource(filePath.toUri());
            return (Resource) resource;
        }catch(MalformedURLException e){
            throw new RuntimeException("file not found",e);
        }
    }
}
