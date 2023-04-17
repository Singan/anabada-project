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
        String fileName = "C:/anabada/images" ;
        System.out.println("fileName"+fileName);
        try{
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

    public List<String> fileSave(MultipartFile[] multipartFiles) {
        String fileName = "C:/anabada/product/" ;
        File file = new File(fileName);
        file.mkdirs();
        List<String> resultPath = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            try{
                String filePath = fileName+multipartFile.getOriginalFilename();

                multipartFile.transferTo(new File(filePath));

                resultPath.add(filePath);
            } catch (Exception e) {
                throw new RuntimeException("파일 저장 실패");
            }
        }
        return resultPath;
    }
}
