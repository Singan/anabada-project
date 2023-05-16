package com.anabada.dto.request_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDto {
    private String updatePw;
    private String confirmPw;
    private String updateAddr;
    private String updateDetailAddr;
    private String updateWishAddr;
    private MultipartFile updateImage;
}
