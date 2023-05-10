package com.anabada.etc;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileProcessor {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public String fileSave(MultipartFile multipartFile,String type,Long no) {
        String fileName = type+"/"+no+"/"+multipartFile.getOriginalFilename();
//        fileName = "/Users/hwi/anabada/images";
        try{
            if(!isImageCheck(multipartFile.getOriginalFilename())){
                throw new RuntimeException(multipartFile.getOriginalFilename()+"은 이미지가 아닙니다.");
            }
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            amazonS3Client.putObject(bucket,fileName,multipartFile.getInputStream(),objectMetadata);
            return "https://"+bucket+fileName;
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패");
        }

    }

    public List<String> fileSave(List<MultipartFile> multipartFiles) {
        String fileName = "C:/anabada/product/";
//        String fileName = "/Users/hwi/anabada/images";
        File file = new File(fileName);
        file.mkdirs();
        List<String> resultPath = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            try{
                if(!isImageCheck(multipartFile.getOriginalFilename())){
                    continue;
                }
                String filePath = fileName+multipartFile.getOriginalFilename();

                multipartFile.transferTo(new File(filePath));

                resultPath.add(filePath);
            } catch (Exception e) {
                throw new RuntimeException("파일 저장 실패");
            }
        }
        return resultPath;
    }

    public boolean isImageCheck(String fileName){
        int dot = fileName.lastIndexOf(".");
        String extension = fileName.substring(dot).toLowerCase();
        String[] extensionList = {"png","jpg","jpeg","gif","img"};
        if(List.of(extensionList).contains(extension)){
            return false;
        }
        return true;
    }
}
