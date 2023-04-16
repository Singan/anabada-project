package com.anabada.etc;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Component
@NoArgsConstructor
public class FileProcessor {
    public String fileSave(MultipartFile multipartFile) {
        System.out.println(multipartFile.equals(null));
        String fileName = "C:/anabada/image" + multipartFile.getOriginalFilename();

        System.out.println("fileName"+fileName);
        try{
            multipartFile.transferTo(new File(fileName));
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패");
        }

    }

    public void fileSave(MultipartFile... multipartFiles) {
        for (MultipartFile multipartFile : multipartFiles) {

        }
    }
}
