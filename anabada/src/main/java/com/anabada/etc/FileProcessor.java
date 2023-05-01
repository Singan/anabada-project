package com.anabada.etc;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class FileProcessor {
    public String fileSave(MultipartFile multipartFile) {
//        String fileName = "C:/anabada/images";
        String fileName = "/Users/hwi/anabada/images";
        System.out.println("fileName"+fileName);
        try{

            if(!isImageCheck(multipartFile.getOriginalFilename())){
                throw new RuntimeException(multipartFile.getOriginalFilename()+"은 이미지가 아닙니다.");
            }
            File file = new File(fileName);
            file.mkdirs();
            fileName = fileName+"/"+multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(fileName));
            System.out.println(fileName);
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패");
        }

    }

    public List<String> fileSave(List<MultipartFile> multipartFiles) {
        String fileName = "C:/anabada/product/";
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
